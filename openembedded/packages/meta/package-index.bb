DESCRIPTION = "Rebuild the package index"
LICENSE = "MIT"

INHIBIT_DEFAULT_DEPS = "1"
ALLOW_EMPTY = "1"
PACKAGES = ""

do_fetch() {
}
do_unpack() {
}
do_patch() {
}
do_configure() {
}
do_compile() {
}
do_install() {
}
do_stage() {
}

do_build[nostamp] = "1"
do_build[dirs] = "${DEPLOY_DIR_IPK}"
do_build() {
	set -ex
	package_update_index_ipk
	set +ex
}
