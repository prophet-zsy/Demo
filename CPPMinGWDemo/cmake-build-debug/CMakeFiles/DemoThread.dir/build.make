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
include CMakeFiles/DemoThread.dir/depend.make

# Include the progress variables for this target.
include CMakeFiles/DemoThread.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/DemoThread.dir/flags.make

CMakeFiles/DemoThread.dir/demo_thread.cpp.obj: CMakeFiles/DemoThread.dir/flags.make
CMakeFiles/DemoThread.dir/demo_thread.cpp.obj: ../demo_thread.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=C:\Users\admin\Desktop\Demo\CPPMinGWDemo\cmake-build-debug\CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Building CXX object CMakeFiles/DemoThread.dir/demo_thread.cpp.obj"
	C:\software\mingw64\bin\g++.exe $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles\DemoThread.dir\demo_thread.cpp.obj -c C:\Users\admin\Desktop\Demo\CPPMinGWDemo\demo_thread.cpp

CMakeFiles/DemoThread.dir/demo_thread.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/DemoThread.dir/demo_thread.cpp.i"
	C:\software\mingw64\bin\g++.exe $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E C:\Users\admin\Desktop\Demo\CPPMinGWDemo\demo_thread.cpp > CMakeFiles\DemoThread.dir\demo_thread.cpp.i

CMakeFiles/DemoThread.dir/demo_thread.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/DemoThread.dir/demo_thread.cpp.s"
	C:\software\mingw64\bin\g++.exe $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S C:\Users\admin\Desktop\Demo\CPPMinGWDemo\demo_thread.cpp -o CMakeFiles\DemoThread.dir\demo_thread.cpp.s

# Object files for target DemoThread
DemoThread_OBJECTS = \
"CMakeFiles/DemoThread.dir/demo_thread.cpp.obj"

# External object files for target DemoThread
DemoThread_EXTERNAL_OBJECTS =

DemoThread.exe: CMakeFiles/DemoThread.dir/demo_thread.cpp.obj
DemoThread.exe: CMakeFiles/DemoThread.dir/build.make
DemoThread.exe: CMakeFiles/DemoThread.dir/linklibs.rsp
DemoThread.exe: CMakeFiles/DemoThread.dir/objects1.rsp
DemoThread.exe: CMakeFiles/DemoThread.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir=C:\Users\admin\Desktop\Demo\CPPMinGWDemo\cmake-build-debug\CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Linking CXX executable DemoThread.exe"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles\DemoThread.dir\link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
CMakeFiles/DemoThread.dir/build: DemoThread.exe

.PHONY : CMakeFiles/DemoThread.dir/build

CMakeFiles/DemoThread.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles\DemoThread.dir\cmake_clean.cmake
.PHONY : CMakeFiles/DemoThread.dir/clean

CMakeFiles/DemoThread.dir/depend:
	$(CMAKE_COMMAND) -E cmake_depends "MinGW Makefiles" C:\Users\admin\Desktop\Demo\CPPMinGWDemo C:\Users\admin\Desktop\Demo\CPPMinGWDemo C:\Users\admin\Desktop\Demo\CPPMinGWDemo\cmake-build-debug C:\Users\admin\Desktop\Demo\CPPMinGWDemo\cmake-build-debug C:\Users\admin\Desktop\Demo\CPPMinGWDemo\cmake-build-debug\CMakeFiles\DemoThread.dir\DependInfo.cmake --color=$(COLOR)
.PHONY : CMakeFiles/DemoThread.dir/depend

