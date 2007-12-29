# rpm2cpio-native build file
# Copyright (C) 2005, Advanced Micro Devices, Inc.  All Rights Reserved
# Released under the MIT license (see COPYING.MIT)

DEPENDS="perl-native"
LICENSE="BSD"
SRC_URI="${FREEBSD_MIRROR}/ports/packages/archivers/rpm2cpio-1.2_2.tbz"

inherit native

S="${WORKDIR}"

do_compile() {
}

do_stage() {
	install -d ${STAGING_BINDIR}
	sed -e '1,1s|${bindir}/|${bindir}/env |' bin/rpm2cpio.pl \
		> ${STAGING_BINDIR}/rpm2cpio.pl

	my_PERL="/usr/bin/env perl"
	sed -e "s%/[a-zA-Z0-9/]*/bin/perl%$my_PERL%g" -i ${STAGING_BINDIR}/rpm2cpio.pl

	chmod 0755 ${STAGING_BINDIR}/rpm2cpio.pl
}
