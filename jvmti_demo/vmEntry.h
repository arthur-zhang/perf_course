#pragma once

#include <jvmti.h>
#include <jni.h>

typedef struct {
    jint bci;
    jmethodID method_id;
} ASGCT_CallFrame;

typedef struct {
    JNIEnv *env;
    jint num_frames;
    ASGCT_CallFrame *frames;
} ASGCT_CallTrace;
typedef struct {
    void *unused[38];

    jstring (JNICALL *ExecuteDiagnosticCommand)(JNIEnv *, jstring);
} VMManagement;

