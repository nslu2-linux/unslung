require u-boot.inc

DEFAULT_PREFERENCE = "-1"

SRC_URI = "ftp://ftp.denx.de/pub/u-boot/u-boot-${PV}.tar.bz2 \
          file://u-boot-make381-fix.patch;patch=1"

SRC_URI_append_gumstix = "\
						  file://u-boot-autoscript.patch;patch=1 \
						  file://u-boot-base.patch;patch=1 \
						  file://u-boot-crc-warning-not-so-scary.patch;patch=1 \
						  file://u-boot-flash-protect-fixup.patch;patch=1 \
						  file://u-boot-fw_printenv.patch;patch=1 \
						  file://u-boot-install.patch;patch=1 \
						  file://u-boot-jerase-cmd.patch;patch=1 \
						  file://u-boot-jffs2-new-nodetypes.patch;patch=1 \
						  file://u-boot-loadb-safe.patch;patch=1 \
						  file://u-boot-mmc-init.patch;patch=1 \
						  file://u-boot-mmcclk-alternate.patch;patch=1 \
						  file://u-boot-smc91x-multi.patch;patch=1 \
						  file://u-boot-zzz-osx.patch;patch=1"
SRC_URI_append_amsdelta = "\
	http://the.earth.li/pub/e3/u-boot-amsdelta-20060519.diff;patch=1"

SRC_URI_append_dht-walnut= "\
        file://u-boot-dht-walnut-df2.patch;patch=1"

SRC_URI_append_avr32= "\
             http://avr32linux.org/twiki/pub/Main/UbootPatches/u-boot-1.1.4-avr1.patch.bz2;patch=1"


EXTRA_OEMAKE_gumstix = "CROSS_COMPILE=${TARGET_PREFIX} GUMSTIX_400MHZ=${GUMSTIX_400MHZ}"
TARGET_LDFLAGS = ""

UBOOT_MACHINE_dht-walnut = "walnut_config"
UBOOT_MACHINE_atngw100 = "atngw_config"

def gumstix_mhz(d):
	import bb
        m = bb.data.getVar('GUMSTIX_400MHZ', d, 1)
	if 'y' == m:
		return '400'
	else:
		return '200'

UBOOT_IMAGE_gumstix = "u-boot-${MACHINE}-${@gumstix_mhz(d)}Mhz-${PV}-${PR}.bin"

inherit base

do_compile () {
	oe_runmake ${UBOOT_MACHINE}
	oe_runmake all
}
