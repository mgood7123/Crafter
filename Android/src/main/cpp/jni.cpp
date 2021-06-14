//
// Created by Matthew Good on 29/5/21.
//

#include <Crafter/Demo/Demo.h>

int argc = 0;
char ** argv = nullptr;

CRAFTER_PACKAGE_MAIN(
        Java_com_smallville7123_crafter_MagnumEGLSurfaceView_00024MagnumRenderer,
        Crafter::Demo::Packages::CubeWithCamera,
        argc, argv
)

CRAFTER_PACKAGE_MAIN(
    Java_com_smallville7123_crafter_MagnumEGLTextureView_00024MagnumRenderer,
    Crafter::Demo::Packages::Cube,
    argc, argv
)
