include ${PN}.inc
    
PV = "${OPIE_CVS_PV}"

SRC_URI = "${HANDHELDS_CVS};module=opie/core/pim/datebook/holiday/national \
           ${HANDHELDS_CVS};module=opie/etc/nationaldays "
