DESCRIPTION = "Image for development testing"
PR = "r2"
LICENSE = "MIT"

inherit task

DEVIMAGE_EXTRA_RDEPENDS ?= ""
DEVIMAGE_EXTRA_RRECOMMENDS ?= ""

RDEPENDS = "\
    devimage \
    busybox dropbear udev \
    util-linux-mount \
    module-init-tools pcmciautils \
    wireless-tools wpa-supplicant \
    irda-utils acx-firmware \
    kexec-tools \
    ${DEVIMAGE_EXTRA_RDEPENDS} \
    "

RRECOMMENDS = "\
    kernel-module-msdos \
    kernel-module-vfat \
    kernel-modules \
    ${DEVIMAGE_EXTRA_RRECOMMENDS} \
    "
