cmake_minimum_required(VERSION 3.19)
project(CMinGWDemo C)

set(CMAKE_C_STANDARD 11)

add_executable(CDemo main.c header_source_test/src/header_test.c smart_ptr/xxx_ptr_test.cpp smart_ptr/xxx_ptr_test.h)