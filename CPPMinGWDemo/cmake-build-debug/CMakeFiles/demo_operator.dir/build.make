# CMAKE generated file: DO NOT EDIT!
# Generated by "MinGW Makefiles" Generator, CMake Version 3.19

# Delete rule output on recipe failure.
.DELETE_ON_ERROR:


#=============================================================================
# Special targets provided by cmake.

# Disable implicit rules so canonical targets will work.
.SUFFIXES:


# Disable VCS-based implicit rules.
% : %,v


# Disable VCS-based implicit rules.
% : RCS/%


# Disable VCS-based implicit rules.
% : RCS/%,v


# Disable VCS-based implicit rules.
% : SCCS/s.%


# Disable VCS-based implicit rules.
% : s.%


.SUFFIXES: .hpux_make_needs_suffix_list


# Command-line flag to silence nested $(MAKE).
$(VERBOSE)MAKESILENT = -s

#Suppress display of executed commands.
$(VERBOSE).SILENT:

# A target that is always out of date.
cmake_force:

.PHONY : cmake_force

#=============================================================================
# Set environment variables for the build.

SHELL = cmd.exe

# The CMake executable.
CMAKE_COMMAND = "C:\software\CLion\CLion 2021.1.2\bin\cmake\win\bin\cmake.exe"

# The command to remove a file.
RM = "C:\software\CLion\CLion 2021.1.2\bin\cmake\win\bin\cmake.exe" -E rm -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = C:\Users\admin\Desktop\Demo\CPPMinGWDemo

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = C:\Users\admin\Desktop\Demo\CPPMinGWDemo\cmake-build-debug

# Include any dependencies generated for this target.
include CMakeFiles/demo_operator.dir/depend.make

# Include the progress variables for this target.
include CMakeFiles/demo_operator.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/demo_operator.dir/flags.make

CMakeFiles/demo_operator.dir/cppGrammar/DemoOperator.cpp.obj: CMakeFiles/demo_operator.dir/flags.make
CMakeFiles/demo_operator.dir/cppGrammar/DemoOperator.cpp.obj: ../cppGrammar/DemoOperator.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=C:\Users\admin\Desktop\Demo\CPPMinGWDemo\cmake-build-debug\CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Building CXX object CMakeFiles/demo_operator.dir/cppGrammar/DemoOperator.cpp.obj"
	C:\software\mingw64\bin\g++.exe $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles\demo_operator.dir\cppGrammar\DemoOperator.cpp.obj -c C:\Users\admin\Desktop\Demo\CPPMinGWDemo\cppGrammar\DemoOperator.cpp

CMakeFiles/demo_operator.dir/cppGrammar/DemoOperator.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/demo_operator.dir/cppGrammar/DemoOperator.cpp.i"
	C:\software\mingw64\bin\g++.exe $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E C:\Users\admin\Desktop\Demo\CPPMinGWDemo\cppGrammar\DemoOperator.cpp > CMakeFiles\demo_operator.dir\cppGrammar\DemoOperator.cpp.i

CMakeFiles/demo_operator.dir/cppGrammar/DemoOperator.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/demo_operator.dir/cppGrammar/DemoOperator.cpp.s"
	C:\software\mingw64\bin\g++.exe $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S C:\Users\admin\Desktop\Demo\CPPMinGWDemo\cppGrammar\DemoOperator.cpp -o CMakeFiles\demo_operator.dir\cppGrammar\DemoOperator.cpp.s

# Object files for target demo_operator
demo_operator_OBJECTS = \
"CMakeFiles/demo_operator.dir/cppGrammar/DemoOperator.cpp.obj"

# External object files for target demo_operator
demo_operator_EXTERNAL_OBJECTS =

demo_operator.exe: CMakeFiles/demo_operator.dir/cppGrammar/DemoOperator.cpp.obj
demo_operator.exe: CMakeFiles/demo_operator.dir/build.make
demo_operator.exe: CMakeFiles/demo_operator.dir/linklibs.rsp
demo_operator.exe: CMakeFiles/demo_operator.dir/objects1.rsp
demo_operator.exe: CMakeFiles/demo_operator.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir=C:\Users\admin\Desktop\Demo\CPPMinGWDemo\cmake-build-debug\CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Linking CXX executable demo_operator.exe"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles\demo_operator.dir\link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
CMakeFiles/demo_operator.dir/build: demo_operator.exe

.PHONY : CMakeFiles/demo_operator.dir/build

CMakeFiles/demo_operator.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles\demo_operator.dir\cmake_clean.cmake
.PHONY : CMakeFiles/demo_operator.dir/clean

CMakeFiles/demo_operator.dir/depend:
	$(CMAKE_COMMAND) -E cmake_depends "MinGW Makefiles" C:\Users\admin\Desktop\Demo\CPPMinGWDemo C:\Users\admin\Desktop\Demo\CPPMinGWDemo C:\Users\admin\Desktop\Demo\CPPMinGWDemo\cmake-build-debug C:\Users\admin\Desktop\Demo\CPPMinGWDemo\cmake-build-debug C:\Users\admin\Desktop\Demo\CPPMinGWDemo\cmake-build-debug\CMakeFiles\demo_operator.dir\DependInfo.cmake --color=$(COLOR)
.PHONY : CMakeFiles/demo_operator.dir/depend
