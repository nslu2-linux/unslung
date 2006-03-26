include esmart.inc
PR = "r2"

SRC_URI = "cvs://anonymous@thinktux.net/root;module=e17/libs/esmart;date=${PV}"
SRC_URI += "file://ecore-fix.patch;patch=1 \
            file://include-stdio.patch;patch=1 \
            file://compile-fix.patch;patch=1"
S = "${WORKDIR}/esmart"

myheaders = "esmart_container/Esmart_Container.h \
             esmart_draggies/Esmart_Draggies.h \
             esmart_file_dialog/Esmart_File_Dialog.h \
             esmart_text_entry/Esmart_Text_Entry.h \
             esmart_textarea/Esmart_Textarea.h \
             esmart_thumb/Esmart_Thumb.h \
             esmart_trans_x11/Esmart_Trans_X11.h"

mylibraries = "esmart_container \
               esmart_draggies \
               esmart_file_dialog \
               esmart_text_entry \
               esmart_textarea \
               esmart_thumb \
               esmart_trans_x11"

do_compile_prepend() {
	find ${S} -type f -name "*.[ch]" | xargs sed -i 's:NULL:0:g'
}