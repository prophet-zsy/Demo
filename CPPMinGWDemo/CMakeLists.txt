cmake_minimum_required(VERSION 3.19)
project(CPPMinGWDemo)

set(CMAKE_CXX_STANDARD 14)

add_executable(CPPMinGWDemo main.cpp demo_thread.cpp demo_thread.h)   # 将括号中的三个文件一起编译成可执行文件
add_executable(cppGrammar cppGrammar/main.cpp cppGrammar/StringDemo.cpp cppGrammar/StringDemo.h cppGrammar/DemoTemplate.cpp cppGrammar/DemoTemplate.h)
add_executable(DemoOperator cppGrammar/DemoOperator.cpp)
add_executable(demo_ptr_ref cppGrammar/demo_ptr_ref.cpp)



add_executable(CGrammar cGrammar/main.c)
add_executable(Sort Algorithm/Sort.cpp)
add_executable(OJ Algorithm/OJ.cpp)
add_executable(Algorithm Algorithm/Main.cpp)



#add_library(b SHARED B/b.cpp B/b.h)



#link_libraries(b)
