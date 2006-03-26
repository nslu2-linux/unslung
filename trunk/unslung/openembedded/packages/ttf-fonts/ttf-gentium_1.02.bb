DESCRIPTION = "Gentium fonts - TTF Version"
LICENSE = "SIL Open Font License"
HOMEPAGE = "http://scripts.sil.org/gentium"
LICENSE_URL = "http://scripts.sil.org/cms/scripts/page.php?site_id=nrsi&item_id=OFL"

SRC_URI = "${DEBIAN_MIRROR}/non-free/t/ttf-gentium/ttf-gentium_${PV}.orig.tar.gz \
           file://OFL.gz"

include ttf.inc

S = "${WORKDIR}/ttf-sil-gentium-${PV}"

do_install_append() {

    install -d ${D}${datadir}/doc/ttf-gentium/
    install -d ${D}${datadir}/doc/ttf-gentium-alt/

    install -m 0644 ${WORKDIR}/OFL ${D}${datadir}/doc/ttf-gentium/
    install -m 0644 ${WORKDIR}/OFL ${D}${datadir}/doc/ttf-gentium-alt/

}


PACKAGES = "${PN} ${PN}-alt"

FILES_ttf-gentium-alt = "${datadir}/fonts/truetype/GenAI*.ttf \
                         ${datadir}/fonts/truetype/GenAR*.ttf \
			 ${datadir}/doc/ttf-gentium-alt/*"

FILES_${PN} = "${datadir}/fonts/truetype/GenI*.ttf \
               ${datadir}/fonts/truetype/GenR*.ttf \
	       ${datadir}/doc/ttf-gentium/*"

#
# License
# 
# Gentium is released under the SIL Open Font License - please read it carefully
# and do not download the fonts unless you agree to the terms of the license:
# 
# This Font Software is Copyright (c) 2003-2005, SIL International
# (http://scripts.sil.org/). All Rights Reserved.
# 
# "Gentium" is a Reserved Font Name for this Font Software. "SIL" is a Reserved
# Font Name for this Font Software.
# 
# This Font Software is licensed under the SIL Open Font License, Version 1.0. No
# modification of the license is permitted, only verbatim copy is allowed. This
# license is copied below, and is also available with a FAQ
# at:http://scripts.sil.org/OFL SIL OPEN FONT LICENSE
# 
# Version 1.0 - 22 November 2005 
#
# PREAMBLE
# 
# The goals of the Open Font License (OFL) are to stimulate worldwide development
# of cooperative font projects, to support the font creation efforts of academic
# and linguistic communities, and to provide an open framework in which fonts may
# be shared and improved in partnership with others.
# 
# The OFL allows the licensed fonts to be used, studied, modified and
# redistributed freely as long as they are not sold by themselves. The fonts,
# including any derivative works, can be bundled, embedded, redistributed and
# sold with any software provided that the font names of derivative works are
# changed. The fonts and derivatives, however, cannot be released under any other
# type of license. 
#
# DEFINITIONS
# 
# "Font Software" refers to any and all of the following:
# 
#     * font files data files source code build scripts documentation
# 
# "Reserved Font Name" refers to the Font Software name as seen by users and any
# other names as specified after the copyright statement.
# 
# "Standard Version" refers to the collection of Font Software components as
# distributed by the Copyright Holder.
# 
# "Modified Version" refers to any derivative font software made by adding to,
# deleting, or substituting -- in part or in whole -- any of the components of
# the Standard Version, by changing formats or by porting the Font Software to a
# new environment.
# 
# "Author" refers to any designer, engineer, programmer, technical writer or
# other person who contributed to the Font Software. 
#
# PERMISSION & CONDITIONS
# 
# Permission is hereby granted, free of charge, to any person obtaining a copy of
# the Font Software, to use, study, copy, merge, embed, modify, redistribute, and
# sell modified and unmodified copies of the Font Software, subject to the
# following conditions:
# 
# 1) Neither the Font Software nor any of its individual components, in Standard
# or Modified Versions, may be sold by itself.
# 
# 2) Standard or Modified Versions of the Font Software may be bundled,
# redistributed and sold with any software, provided that each copy contains the
# above copyright notice and this license. These can be included either as
# stand-alone text files, human-readable headers or in the appropriate
# machine-readable metadata fields within text or binary files as long as those
# fields can be easily viewed by the user.
# 
# 3) No Modified Version of the Font Software may use the Reserved Font Name(s),
# in part or in whole, unless explicit written permission is granted by the
# Copyright Holder. This restriction applies to all references stored in the Font
# Software, such as the font menu name and other font description fields, which
# are used to differentiate the font from others.
# 
# 4) The name(s) of the Copyright Holder or the Author(s) of the Font Software
# shall not be used to promote, endorse or advertise any Modified Version, except
# to acknowledge the contribution(s) of the Copyright Holder and the Author(s) or
# with their explicit written permission.
# 
# 5) The Font Software, modified or unmodified, in part or in whole, must be
# distributed using this license, and may not be distributed under any other
# license. 
#
# TERMINATION
# 
# This license becomes null and void if any of the above conditions are not met.
#
# DISCLAIMER
# 
# THE FONT SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
# IMPLIED, INCLUDING BUT NOT LIMITED TO ANY WARRANTIES OF MERCHANTABILITY,
# FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT OF COPYRIGHT, PATENT,
# TRADEMARK, OR OTHER RIGHT. IN NO EVENT SHALL THE COPYRIGHT HOLDER BE LIABLE FOR
# ANY CLAIM, DAMAGES OR OTHER LIABILITY, INCLUDING ANY GENERAL, SPECIAL,
# INDIRECT, INCIDENTAL, OR CONSEQUENTIAL DAMAGES, WHETHER IN AN ACTION OF
# CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF THE USE OR INABILITY TO USE
# THE FONT SOFTWARE OR FROM OTHER DEALINGS IN THE FONT SOFTWARE.
