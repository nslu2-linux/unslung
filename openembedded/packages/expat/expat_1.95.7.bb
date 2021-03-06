require expat.inc
PR = "r1"

SRC_URI += "file://autotools.patch;patch=1"

inherit lib_package

do_configure () {
	rm -f ${S}/conftools/libtool.m4
	autotools_do_configure
}

do_stage () {
	install -m 0644 ${S}/lib/expat.h ${STAGING_INCDIR}/
	oe_libinstall -so libexpat ${STAGING_LIBDIR}
}

do_install () {
	oe_runmake prefix="${D}${prefix}" \
		bindir="${D}${bindir}" \
		libdir="${D}${libdir}" \
		includedir="${D}${includedir}" \
		install
}
