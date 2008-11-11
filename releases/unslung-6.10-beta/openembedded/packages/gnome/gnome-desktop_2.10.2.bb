LICENSE = "GPL"
SECTION = "x11/gnome"
PR = "r1"
DESCRIPTION = "GNOME library for reading .desktop files"
inherit gnome pkgconfig

DEPENDS = "gnome-common libgnomeui"

EXTRA_AUTORECONF = "-I ${STAGING_DATADIR}/aclocal/gnome2-macros"

do_configure_prepend () {
	cp ${STAGING_DATADIR}/gnome-common/data/omf.make ${S}
}

do_stage () {
autotools_stage_all
}