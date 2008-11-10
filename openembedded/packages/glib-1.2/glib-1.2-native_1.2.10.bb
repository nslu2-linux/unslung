PR = "r0"
LICENSE = "LGPL"
DESCRIPTION = "GLib 1.2 is a deprecated libray to \
provide support for old glib 1.2 based applications"
SECTION = "libs"
PRIORITY = "optional"
DEPENDS = ""

FILESDIR = "${@os.path.dirname(bb.data.getVar('FILE',d,1))}/glib-1.2-${PV}"

EXTRA_OECONF = "--disable-debug"

SRC_URI = "ftp://ftp.gtk.org/pub/gtk/v1.2/glib-${PV}.tar.gz \
           file://glibconfig-sysdefs.h \
           file://depcomp \
           file://glib-reconf-fix;patch=1 \
           file://posix-conf-changes;patch=1 \
           file://gcc-3.4-pretty_function;patch=1"


S = "${WORKDIR}/glib-${PV}"

inherit autotools pkgconfig native gettext

acpaths = ""
do_configure_prepend () {
	install -m 0644 ${WORKDIR}/glibconfig-sysdefs.h .
	install -m 0644 ${WORKDIR}/depcomp .
	rm -f ltconfig acinclude.m4 libtool ltmain.sh
}


do_stage () {
	oe_libinstall -so libglib ${STAGING_LIBDIR}
	oe_libinstall -so -C gmodule libgmodule ${STAGING_LIBDIR}
	oe_libinstall -so -C gthread libgthread ${STAGING_LIBDIR}
	autotools_stage_includes
	install -d ${STAGING_INCDIR}/glib-1.2
	install -m 0755 ${S}/glibconfig.h ${STAGING_INCDIR}/glib-1.2/glibconfig.h
	install -d ${STAGING_DATADIR}/aclocal
	install -m 0644 ${S}/glib.m4 ${STAGING_DATADIR}/aclocal/glib-1.2.m4
}

do_install () {
	:
}
