MIRRORS_prepend () {
${GNU_MIRROR}/gcc/releases/    ftp://gcc.gnu.org/pub/gcc/releases/
${GNU_MIRROR}/gcc/	http://mirrors.rcn.net/pub/sourceware/gcc/releases/
${GNU_MIRROR}/gcc/releases/    http://gcc.get-software.com/releases/
${GNU_MIRROR}/gcc/	http://gcc.get-software.com/releases/
}

gcclibdir ?= "${libdir}/gcc"
S = "${WORKDIR}/gcc-${PV}"
B = "${S}/build.${HOST_SYS}.${TARGET_SYS}"
BINV ?= "${PV}"

# gcj doesn't work on some architectures
JAVA ?= ",java"
JAVA_arm ?= ""
JAVA_armeb ?= ""
JAVA_mipsel ?= ""
JAVA_sh3 ?= ""
# gcc4-build sets this to fortran
FORTRAN ?= ",f77"
LANGUAGES ?= "c,c++${FORTRAN}${JAVA}"

EXTRA_OECONF = "${@['--enable-clocale=generic', ''][bb.data.getVar('USE_NLS', d, 1) != 'no']} \
                --with-gnu-ld \
                --enable-shared \
                --enable-target-optspace \
                --enable-languages=${LANGUAGES} \
                --enable-threads=posix \
                --enable-multilib \
                --enable-c99 \
                --enable-long-long \
                --enable-symvers=gnu \
                --enable-libstdcxx-pch \
                --program-prefix=${TARGET_PREFIX} \
                ${EXTRA_OECONF_PATHS} \
                ${EXTRA_OECONF_DEP}"

EXTRA_OECONF_PATHS = " \
                      --with-local-prefix=${prefix}/local \
                      --with-gxx-include-dir=${includedir}/c++/${BINV}"

# Build uclibc compilers without cxa_atexit support
EXTRA_OECONF_append_linux               = " --enable-__cxa_atexit"
EXTRA_OECONF_append_linux-gnueabi       = " --enable-__cxa_atexit"
EXTRA_OECONF_append_linux-uclibc        = " --disable-__cxa_atexit"
EXTRA_OECONF_append_linux-uclibcgnueabi = " --disable-__cxa_atexit"
EXTRA_OECONF += "${@get_gcc_fpu_setting(bb, d)}"
CPPFLAGS = ""

# Used by configure to define additional values for FLAGS_FOR_TARGET -
# passed to all the compilers.
ARCH_FLAGS_FOR_TARGET = ""
#NOTE: not tested on other platforms, the following is probably correct
# everywhere!
ARCH_FLAGS_FOR_TARGET_slugos = "${TARGET_CC_ARCH}"
ARCH_FLAGS_FOR_TARGET_unslung = "${TARGET_CC_ARCH}"
EXTRA_OEMAKE += "ARCH_FLAGS_FOR_TARGET='${ARCH_FLAGS_FOR_TARGET}'"

require gcc-fpu.inc

do_configure () {
	# Setup these vars for cross building only
	# ... because foo_FOR_TARGET apparently gets misinterpreted inside the
	# gcc build stuff when the build is producing a cross compiler - i.e.
	# when the 'current' target is the 'host' system, and the host is not
	# the target (because the build is actually making a cross compiler!)
	if [ "${BUILD_SYS}" != "${HOST_SYS}" ]; then
		export CC_FOR_TARGET="${CC}"
		export GCC_FOR_TARGET="${CC}"
		export CXX_FOR_TARGET="${CXX}"
		export AS_FOR_TARGET="${HOST_PREFIX}as"
		export LD_FOR_TARGET="${HOST_PREFIX}ld"
		export NM_FOR_TARGET="${HOST_PREFIX}nm"
		export AR_FOR_TARGET="${HOST_PREFIX}ar"
		export GFORTRAN_FOR_TARGET="gfortran"
		export RANLIB_FOR_TARGET="${HOST_PREFIX}ranlib"
	fi
	export CC_FOR_BUILD="${BUILD_CC}"
	export CXX_FOR_BUILD="${BUILD_CXX}"
	export CFLAGS_FOR_BUILD="${BUILD_CFLAGS}"
	export CPPFLAGS_FOR_BUILD="${BUILD_CPPFLAGS}"
	export CXXFLAGS_FOR_BUILD="${BUILD_CXXFLAGS}"
	export LDFLAGS_FOR_BUILD="${BUILD_LDFLAGS}"
	export ARCH_FLAGS_FOR_TARGET="${ARCH_FLAGS_FOR_TARGET}"
	(cd ${S} && gnu-configize) || die "failure running gnu-configize"
	oe_runconf
}
