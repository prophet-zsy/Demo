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
include CMakeFiles/CMinGWDemo.dir/depend.make

# Include the progress variables for this target.
include CMakeFiles/CMinGWDemo.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/CMinGWDemo.dir/flags.make

CMakeFiles/CMinGWDemo.dir/cGrammar/main.c.obj: CMakeFiles/CMinGWDemo.dir/flags.make
CMakeFiles/CMinGWDemo.dir/cGrammar/main.c.obj: ../cGrammar/main.c
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=C:\Users\admin\Desktop\Demo\CPPMinGWDemo\cmake-build-debug\CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Building C object CMakeFiles/CMinGWDemo.dir/cGrammar/main.c.obj"
	C:\software\mingw64\bin\gcc.exe $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -o CMakeFiles\CMinGWDemo.dir\cGrammar\main.c.obj -c C:\Users\admin\Desktop\Demo\CPPMinGWDemo\cGrammar\main.c

CMakeFiles/CMinGWDemo.dir/cGrammar/main.c.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing C source to CMakeFiles/CMinGWDemo.dir/cGrammar/main.c.i"
	C:\software\mingw64\bin\gcc.exe $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -E C:\Users\admin\Desktop\Demo\CPPMinGWDemo\cGrammar\main.c > CMakeFiles\CMinGWDemo.dir\cGrammar\main.c.i

CMakeFiles/CMinGWDemo.dir/cGrammar/main.c.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling C source to assembly CMakeFiles/CMinGWDemo.dir/cGrammar/main.c.s"
	C:\software\mingw64\bin\gcc.exe $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -S C:\Users\admin\Desktop\Demo\CPPMinGWDemo\cGrammar\main.c -o CMakeFiles\CMinGWDemo.dir\cGrammar\main.c.s

# Object files for target CMinGWDemo
CMinGWDemo_OBJECTS = \
"CMakeFiles/CMinGWDemo.dir/cGrammar/main.c.obj"

# External object files for target CMinGWDemo
CMinGWDemo_EXTERNAL_OBJECTS =

CMinGWDemo.exe: CMakeFiles/CMinGWDemo.dir/cGrammar/main.c.obj
CMinGWDemo.exe: CMakeFiles/CMinGWDemo.dir/build.make
CMinGWDemo.exe: CMakeFiles/CMinGWDemo.dir/linklibs.rsp
CMinGWDemo.exe: CMakeFiles/CMinGWDemo.dir/objects1.rsp
CMinGWDemo.exe: CMakeFiles/CMinGWDemo.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir=C:\Users\admin\Desktop\Demo\CPPMinGWDemo\cmake-build-debug\CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Linking C executable CMinGWDemo.exe"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles\CMinGWDemo.dir\link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
CMakeFiles/CMinGWDemo.dir/build: CMinGWDemo.exe

.PHONY : CMakeFiles/CMinGWDemo.dir/build

CMakeFiles/CMinGWDemo.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles\CMinGWDemo.dir\cmake_clean.cmake
.PHONY : CMakeFiles/CMinGWDemo.dir/clean

CMakeFiles/CMinGWDemo.dir/depend:
	$(CMAKE_COMMAND) -E cmake_depends "MinGW Makefiles" C:\Users\admin\Desktop\Demo\CPPMinGWDemo C:\Users\admin\Desktop\Demo\CPPMinGWDemo C:\Users\admin\Desktop\Demo\CPPMinGWDemo\cmake-build-debug C:\Users\admin\Desktop\Demo\CPPMinGWDemo\cmake-build-debug C:\Users\admin\Desktop\Demo\CPPMinGWDemo\cmake-build-debug\CMakeFiles\CMinGWDemo.dir\DependInfo.cmake --color=$(COLOR)
.PHONY : CMakeFiles/CMinGWDemo.dir/depend

