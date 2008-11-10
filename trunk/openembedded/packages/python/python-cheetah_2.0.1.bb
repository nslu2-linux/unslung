DESCRIPTION = "template engine and code generation tool"
SECTION = "devel/python"
PRIORITY = "optional"
LICENSE = "MIT-like"
RDEPENDS = "python-pickle python-pprint"
SRCNAME = "Cheetah"

SRC_URI = "${SOURCEFORGE_MIRROR}/cheetahtemplate/${SRCNAME}-${PV}.tar.gz"
S = "${WORKDIR}/${SRCNAME}-${PV}"

inherit distutils
