cmake_minimum_required(VERSION 3.22)
project(cpu_course)

set(CMAKE_CXX_STANDARD 14)
AUX_SOURCE_DIRECTORY(. SDK_SRC)
add_executable(cpu_course ${SDK_SRC})
