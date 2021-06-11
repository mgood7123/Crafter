cmake_minimum_required(VERSION 3.4)
project(App)

add_subdirectory(${CMAKE_CURRENT_SOURCE_DIR}/../Crafter_SDK/CMAKE_HELPER ${CMAKE_CURRENT_BINARY_DIR}/CMAKE_HELPER)
set_Crafter_SDK_ROOT(${CMAKE_CURRENT_SOURCE_DIR}/../Crafter_SDK)

import_project__Crafter_Magnum()
import_project__Crafter_Core()

testBuilder_add_source(App src/MyApplication.cpp)
testBuilder_add_library(App Crafter_Magnum)
testBuilder_add_library(App Crafter_Core)
testBuilder_build_executable(App EXECUTABLES)