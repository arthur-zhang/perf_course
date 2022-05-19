#include <dlfcn.h>
#include <jvmti.h>
#include <jni.h>
#include <iostream>
#include "jvmti_demo.h"
#include <signal.h>
#include <sys/time.h>
#include "vmEntry.h"

using namespace std;

typedef void (*AsyncGetCallTrace)(ASGCT_CallTrace *, jint, void *);

AsyncGetCallTrace _asyncGetCallTrace;

void *getLibraryHandle(const char *name) {
    void *handle = dlopen(name, RTLD_LAZY);
    if (handle != nullptr) {
        return handle;
    }
    return RTLD_DEFAULT;
}

static jvmtiEnv *_jvmti;

void loadMethodIDs(jvmtiEnv *jvmti, JNIEnv *jni, jclass klass) {
    jint method_count;
    jmethodID *methods;
    if (jvmti->GetClassMethods(klass, &method_count, &methods) == 0) {
        jvmti->Deallocate(reinterpret_cast<unsigned char *>(methods));
    }
}

void loadAllMethodIDs(jvmtiEnv *jvmti, JNIEnv *jni) {
    jint class_count;
    jclass *classes;
    if (jvmti->GetLoadedClasses(&class_count, &classes) == 0) {
        for (int i = 0; i < class_count; ++i) {
            loadMethodIDs(jvmti, jni, classes[i]);
        }
        jvmti->Deallocate(reinterpret_cast<unsigned char *>(classes));
    }
}

void JNICALL VMInit(jvmtiEnv *jvmti, JNIEnv *jni, jthread thread) {
    loadAllMethodIDs(jvmti, jni);
}

void JNICALL VMDeath(jvmtiEnv *jvmti, JNIEnv *jni) {
    std::cout << "VMDeath" << std::endl;
}

static void JNICALL ClassLoad(jvmtiEnv *jvmti, JNIEnv *jni, jthread thread, jclass klass) {
}

static void JNICALL ClassPrepare(jvmtiEnv *jvmti, JNIEnv *jni, jthread thread, jclass klass) {
    loadMethodIDs(jvmti, jni, klass);
}

typedef void (*SigAction)(int, siginfo_t *, void *);

typedef void (*SigHandler)(int);

SigAction installSignalHandler(int signo, SigAction action, SigHandler handler = nullptr) {
    struct sigaction sa;
    struct sigaction oldsa;
    sigemptyset(&sa.sa_mask);

    if (handler != nullptr) {
        sa.sa_handler = handler;
        sa.sa_flags = 0;
    } else {
        sa.sa_sigaction = action;
        sa.sa_flags = SA_SIGINFO | SA_RESTART;
    }

    sigaction(signo, &sa, &oldsa);
    return oldsa.sa_sigaction;
}

static bool vm_init(JavaVM *vm) {
    std::cout << "vm_init" << std::endl;

    if (vm->GetEnv((void **) &_jvmti, JVMTI_VERSION_1_0) != 0) {
        return false;
    }

    void *libjvm = getLibraryHandle("libjvm.so");
    _asyncGetCallTrace = (AsyncGetCallTrace) dlsym(libjvm, "AsyncGetCallTrace");

    jvmtiCapabilities capabilities = {0};
    capabilities.can_generate_all_class_hook_events = 1;
    capabilities.can_retransform_classes = 1;
    capabilities.can_retransform_any_class = 1;
    capabilities.can_generate_vm_object_alloc_events = 0;
    capabilities.can_get_bytecodes = 1;
    capabilities.can_get_constant_pool = 1;
    capabilities.can_get_source_file_name = 1;
    capabilities.can_get_line_numbers = 1;
    capabilities.can_generate_compiled_method_load_events = 1;
    capabilities.can_generate_monitor_events = 1;
    capabilities.can_tag_objects = 1;
    if (_jvmti->AddCapabilities(&capabilities) != 0) {
        std::cout << "AddCapabilities" << std::endl;
    }

    jvmtiEventCallbacks callbacks = {0};
    callbacks.VMInit = VMInit;
    callbacks.VMDeath = VMDeath;
    callbacks.ClassLoad = ClassLoad;
    callbacks.ClassPrepare = ClassPrepare;
    _jvmti->SetEventCallbacks(&callbacks, sizeof(callbacks));

    _jvmti->SetEventNotificationMode(JVMTI_ENABLE, JVMTI_EVENT_VM_DEATH, NULL);
    _jvmti->SetEventNotificationMode(JVMTI_ENABLE, JVMTI_EVENT_CLASS_LOAD, NULL);
    _jvmti->SetEventNotificationMode(JVMTI_ENABLE, JVMTI_EVENT_CLASS_PREPARE, NULL);
    _jvmti->SetEventNotificationMode(JVMTI_ENABLE, JVMTI_EVENT_DYNAMIC_CODE_GENERATED, NULL);
    _jvmti->SetEventNotificationMode(JVMTI_ENABLE, JVMTI_EVENT_VM_INIT, NULL);

    return true;
}

class Event {
public:
    uint32_t id() {
        return *(uint32_t *) this;
    }
};

class ExecutionEvent : public Event {
public:
};

const int CONCURRENCY_LEVEL = 16;

inline uint32_t getLockIndex(int tid) {
    uint32_t lock_index = tid;
    lock_index ^= lock_index >> 8;
    lock_index ^= lock_index >> 4;
    return lock_index % CONCURRENCY_LEVEL;
}

JavaVM *g_jvm;
JNIEnv *getJNIEnv(JavaVM *jvm) {
    JNIEnv *jniEnv = NULL;
    int getEnvStat = jvm->GetEnv((void **) &jniEnv, JNI_VERSION_1_6);
    if (getEnvStat == JNI_EDETACHED || getEnvStat == JNI_EVERSION) {
        jniEnv = NULL;
    }
    return jniEnv;
}
const int maxFramesToCapture = 128;

void recordSample(void *ucontext, uint64_t counter, jint event_type, Event *event) {
    std::cout << "Profiler::recordSample: " << std::endl;

    ASGCT_CallFrame frames[maxFramesToCapture];

    ASGCT_CallTrace trace;
    trace.frames = frames;
    trace.env = getJNIEnv(g_jvm);

    _asyncGetCallTrace(&trace, maxFramesToCapture, ucontext);
}

void signalHandler(int signo, siginfo_t *siginfo, void *ucontext) {
    if (siginfo->si_code <= 0) {
        return;
    }

    uint64_t counter = 0;
    ExecutionEvent event;
    recordSample(ucontext, counter, 0, &event);
}

const int _interval = 100000000;
jint Agent_OnLoad(JavaVM *vm,
                  char *options,
                  void *reserved) {
    cout << "jvmti-demo loaded" << endl;
    g_jvm = vm;
    bool rc;
    rc = vm_init(vm);

    installSignalHandler(SIGPROF, signalHandler);

    long sec = _interval / 1000000000;
    long usec = (_interval % 1000000000) / 1000;
    struct itimerval tv = {{sec, usec}, {sec, usec}};
    setitimer(ITIMER_PROF, &tv, NULL);
    return 0;
}

void Agent_OnUnload(JavaVM *vm) {
    cout << "jvmti-demo unloaded" << endl;
}

