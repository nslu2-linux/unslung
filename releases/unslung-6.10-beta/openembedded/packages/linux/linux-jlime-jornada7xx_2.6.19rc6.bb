SECTION = "kernel"
DESCRIPTION = "JLime Linux kernel for Arm based Jornada 7xx"
LICENSE = "GPL"
PR = "r0"

COMPATIBLE_HOST = "arm.*-linux"
COMPATIBLE_MACHINE = "jornada7xx"

SRC_URI = "${KERNELORG_MIRROR}/pub/linux/kernel/v2.6/linux-2.6.18.tar.gz \
           ${KERNELORG_MIRROR}/pub/linux/kernel/v2.6/testing/patch-2.6.19-rc6.bz2;patch=1 \
	   file://jornada7xx.patch;patch=0 \
           file://defconf_jlime "

S = "${WORKDIR}/linux-2.6.18"

inherit kernel

#Lets let 3.4.x handle the compilation of this one
KERNEL_CCSUFFIX = "-3.4.4"

ARCH = "arm"

FILES_kernel-image = "/boot/${KERNEL_IMAGETYPE}*"

do_configure_prepend() {
	install -m 0644 ${WORKDIR}/defconf_jlime ${S}/.config
}

do_deploy() {
        install -d ${DEPLOY_DIR_IMAGE}
	install -m 0644 arch/$(ARCH)/boot/$(KERNEL_IMAGETYPE) $(DEPLOY_DIR)/images/$(KERNEL_IMAGETYPE)
}
