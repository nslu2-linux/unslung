DESCRIPTION = "APEX Boot Loader"
SECTION = ""
PRIORITY = "optional"
HOMEPAGE = "http://wiki.buici.com/twiki/bin/view/Main/ApexBootloader"
LICENSE = "GPL"
PR = "r3"

SRC_URI = "ftp://ftp.buici.com/pub/apex/apex-${PV}.tar.gz \
	   file://defconfig"
S = ${WORKDIR}/apex-${PV}

CMDLINE_CONSOLE = "console=${@bb.data.getVar("KERNEL_CONSOLE",d,1) or "ttyS0"}"

CMDLINE_ROOT  ?= "root=/dev/mtdblock4 rootfstype=jffs2 init=/linuxrc rw"

CMDLINE_DEBUG ?= ""

EXTRA_OEMAKE_append = " CROSS_COMPILE=${CROSS_DIR}/bin/${HOST_PREFIX}"

oe_runmake() {
	oenote make ${PARALLEL_MAKE} CROSS_COMPILE=${CROSS_DIR}/bin/${TARGET_PREFIX} "$@"
	make ${PARALLEL_MAKE} LDFLAGS= CROSS_COMPILE=${CROSS_DIR}/bin/${TARGET_PREFIX} "$@" || die "oe_runmake failed"
}

# Set the correct CONFIG_USER_xxx_ENDIAN and CONFIG_CMDLINE at the head
# of the .config file and remove any settings in defconfig then append
# defconfig to .config
do_configure() {
	rm -f ${S}/.config
	. ${CONFIG_SITE}
	if [ "x$ac_cv_c_bigendian" = "xyes" -o "x$ac_cv_c_littleendian" = "xno" ]; then
	  sed -e 's/.*CONFIG_USER_BIGENDIAN.*/CONFIG_USER_BIGENDIAN=y/' \
	      -e 's/.*CONFIG_BIGENDIAN.*/CONFIG_BIGENDIAN=y/' \
	      -e 's/.*CONFIG_TARGET_DESCRIPTION.*/CONFIG_TARGET_DESCRIPTION=\"OpenEmbedded NSLU2\/BE (16MiB Flash)\"/' \
	      -e 's|CONFIG_ENV_DEFAULT_CMDLINE=|CONFIG_ENV_DEFAULT_CMDLINE=\"${CMDLINE_CONSOLE} ${CMDLINE_ROOT} ${CMDLINE_DEBUG}\"|' \
	      -e 's|CONFIG_ENV_DEFAULT_CMDLINE_ALT=|CONFIG_ENV_DEFAULT_CMDLINE_ALT=\"${CMDLINE_CONSOLE} ${CMDLINE_ROOT} ${CMDLINE_DEBUG}\"|' \
		${WORKDIR}/defconfig > ${S}/.config
	elif [ "x$ac_cv_c_littleendian" = "xyes" -o "x$ac_cv_c_bigendian" = "xno" ]; then
	  sed -e 's/.*CONFIG_USER_LITTLEENDIAN.*/CONFIG_USER_LITTLEENDIAN=y/' \
	      -e 's/.*CONFIG_LITTLEENDIAN.*/CONFIG_LITTLEENDIAN=y/' \
	      -e 's/.*CONFIG_TARGET_DESCRIPTION.*/CONFIG_TARGET_DESCRIPTION=\"OpenEmbedded NSLU2\/LE (16MiB Flash)\"/' \
	      -e 's|CONFIG_ENV_DEFAULT_CMDLINE=|CONFIG_ENV_DEFAULT_CMDLINE=\"${CMDLINE_CONSOLE} ${CMDLINE_ROOT} ${CMDLINE_DEBUG}\"|' \
	      -e 's|CONFIG_ENV_DEFAULT_CMDLINE_ALT=|CONFIG_ENV_DEFAULT_CMDLINE_ALT=\"${CMDLINE_CONSOLE} ${CMDLINE_ROOT} ${CMDLINE_DEBUG}\"|' \
		${WORKDIR}/defconfig > ${S}/.config
	else
	  oefatal do_configure cannot determine endianess
	fi
	oe_runmake oldconfig
}

DEPENDS += "devio-native"

do_populate_staging() {
	install -d ${STAGING_LOADER_DIR}
	. ${CONFIG_SITE}
	if [ "x$ac_cv_c_bigendian" = "xyes" -o "x$ac_cv_c_littleendian" = "xno" ]; then
		cp src/arch-arm/rom/apex.bin ${STAGING_LOADER_DIR}/apex-nslu2-16mb.bin
	elif [ "x$ac_cv_c_littleendian" = "xyes" -o "x$ac_cv_c_bigendian" = "xno" ]; then
		devio '<<'src/arch-arm/rom/apex.bin >${STAGING_LOADER_DIR}/apex-nslu2-16mb.bin 'xp $,4'
	else
		oefatal do_populate_staging cannot determine endianess
	fi
}
