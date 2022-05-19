#pragma once

#include <jvmti.h>

__attribute__((unused)) JNIEXPORT jint
JNICALL Agent_OnLoad(JavaVM * vm, char * options, void * reserved);

JNIEXPORT void JNICALL
        Agent_OnUnload(JavaVM * vm);