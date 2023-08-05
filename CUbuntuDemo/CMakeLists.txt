cmake_minimum_required(VERSION 3.19)
project(CUbuntuDemo)

set(CMAKE_CXX_STANDARD 14)

#add_executable(CUbuntuDemo main.c ubus_demo/ubus_demo.c ubus_demo/ubus_demo.h get_sys_info/get_sys_info.cpp get_sys_info/get_sys_info.h)
add_executable(CUbuntuDemo get_sys_info/get_sys_info.c get_sys_info/get_sys_info.h)