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
include CMakeFiles/OJ.dir/depend.make

# Include the progress variables for this target.
include CMakeFiles/OJ.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/OJ.dir/flags.make

CMakeFiles/OJ.dir/Algorithm/OJ.cpp.obj: CMakeFiles/OJ.dir/flags.make
CMakeFiles/OJ.dir/Algorithm/OJ.cpp.obj: ../Algorithm/OJ.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=C:\Users\admin\Desktop\Demo\CPPMinGWDemo\cmake-build-debug\CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Building CXX object CMakeFiles/OJ.dir/Algorithm/OJ.cpp.obj"
	C:\software\mingw64\bin\g++.exe $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles\OJ.dir\Algorithm\OJ.cpp.obj -c C:\Users\admin\Desktop\Demo\CPPMinGWDemo\Algorithm\OJ.cpp

CMakeFiles/OJ.dir/Algorithm/OJ.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/OJ.dir/Algorithm/OJ.cpp.i"
	C:\software\mingw64\bin\g++.exe $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E C:\Users\admin\Desktop\Demo\CPPMinGWDemo\Algorithm\OJ.cpp > CMakeFiles\OJ.dir\Algorithm\OJ.cpp.i

CMakeFiles/OJ.dir/Algorithm/OJ.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/OJ.dir/Algorithm/OJ.cpp.s"
	C:\software\mingw64\bin\g++.exe $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S C:\Users\admin\Desktop\Demo\CPPMinGWDemo\Algorithm\OJ.cpp -o CMakeFiles\OJ.dir\Algorithm\OJ.cpp.s

# Object files for target OJ
OJ_OBJECTS = \
"CMakeFiles/OJ.dir/Algorithm/OJ.cpp.obj"

# External object files for target OJ
OJ_EXTERNAL_OBJECTS =

OJ.exe: CMakeFiles/OJ.dir/Algorithm/OJ.cpp.obj
OJ.exe: CMakeFiles/OJ.dir/build.make
OJ.exe: CMakeFiles/OJ.dir/linklibs.rsp
OJ.exe: CMakeFiles/OJ.dir/objects1.rsp
OJ.exe: CMakeFiles/OJ.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir=C:\Users\admin\Desktop\Demo\CPPMinGWDemo\cmake-build-debug\CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Linking CXX executable OJ.exe"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles\OJ.dir\link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
CMakeFiles/OJ.dir/build: OJ.exe

.PHONY : CMakeFiles/OJ.dir/build

CMakeFiles/OJ.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles\OJ.dir\cmake_clean.cmake
.PHONY : CMakeFiles/OJ.dir/clean

CMakeFiles/OJ.dir/depend:
	$(CMAKE_COMMAND) -E cmake_depends "MinGW Makefiles" C:\Users\admin\Desktop\Demo\CPPMinGWDemo C:\Users\admin\Desktop\Demo\CPPMinGWDemo C:\Users\admin\Desktop\Demo\CPPMinGWDemo\cmake-build-debug C:\Users\admin\Desktop\Demo\CPPMinGWDemo\cmake-build-debug C:\Users\admin\Desktop\Demo\CPPMinGWDemo\cmake-build-debug\CMakeFiles\OJ.dir\DependInfo.cmake --color=$(COLOR)
.PHONY : CMakeFiles/OJ.dir/depend
