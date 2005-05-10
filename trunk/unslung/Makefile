# Makefile for Unslung
# Licensed under the GPL v2 or later

unslung-firmware: setup-env conf/local.conf
	(source setup-env ; bitbake unslung-packages)

setup-env:
	echo 'OEROOT='`pwd` > setup-env
	echo 'OESYS=$$OEROOT/bitbake/' >> setup-env
	echo 'PKGDIR=$$OEROOT/openembedded/' >> setup-env
	echo 'OEBUILD=$$OEROOT' >> setup-env
	echo 'BBPATH=$$OEBUILD:$$PKGDIR:$$OESYS' >> setup-env
	echo 'PATH=$$OESYS/bin/:$$PATH' >> setup-env
	echo 'LD_LIBRARY_PATH=' >> setup-env
	echo 'export PATH LD_LIBRARY_PATH BBPATH' >> setup-env
	echo 'export LANG=C' >> setup-env
	echo 'alias bb=bitbake' >> setup-env
	echo 'echo Environment set up for Unslung development.' >> setup-env

conf/local.conf:
	sed -e "s|%%%OEROOT%%%|`pwd`|" conf/local.conf.template > conf/local.conf

clobber:
	rm -rf tmp

update-ignore:
	svn propset svn:ignore -F .svnignore .

unslung-source:
	tar zcvf unslung-source.tar.gz --exclude=.svn Makefile conf openembedded nslu2-linux

# End of Makefile
