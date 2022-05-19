cmake_minimum_required(VERSION 3.19.1)
project(jvmti_demo)

set(CMAKE_CXX_STANDARD 14)
include_directories(
        /Library/Java/JavaVirtualMachines/jdk1.8.0_271.jdk/Contents/Home/include
        /Library/Java/JavaVirtualMachines/jdk1.8.0_271.jdk/Contents/Home/include/darwin/
        /usr/local/jdk/include
        /usr/local/jdk/include/linux
)

add_library(foo MODULE
        jvmti_demo.cpp
        jvmti_demo.h vmEntry.cpp vmEntry.h
        )
set_target_properties(foo PROPERTIES SUFFIX ".dylib")
target_link_libraries(foo ${JNI_LIBRARIES})
