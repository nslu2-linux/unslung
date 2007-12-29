<?php

define('DB_FILENAME', './feeds.db');

$feeds = array(
	array(
		'distro_name'=>'OpenZaurus', 
		'distro_version'=>'3.5.4',
		'feed_base_url'=>'http://ewi546.ewi.utwente.nl/mirror/www.openzaurus.org/official/unstable/3.5.4/feed/',
		'feeds'=>array(
			array(
				'name'=>'base',
				'url'=>'base',
				'type'=>'normal',
			),
			array(
				'name'=>'opie',
				'url'=>'opie',
				'type'=>'normal',
			),
			array(
				'name'=>'x11',
				'url'=>'x11',
				'type'=>'normal',
			),
			array(
				'name'=>'upgrades',
				'url'=>'upgrades',
				'type'=>'upgrades',
			),
			array(
				'name'=>'perl',
				'url'=>'perl',
				'type'=>'normal',
			),
			array(
				'name'=>'python',
				'url'=>'python',
				'type'=>'normal',
			),
			array(
				'name'=>'Collie upgrades',
				'url'=>'upgrades/machine/collie',
				'type'=>'upgrades',
			),
			array(
				'name'=>'Tosa upgrades',
				'url'=>'upgrades/machine/tosa',
				'type'=>'upgrades',
			),
			array(
				'name'=>'Poodle upgrades',
				'url'=>'upgrades/machine/poodle',
				'type'=>'upgrades',
			),
			array(
				'name'=>'Poodle',
				'url'=>'machine/poodle',
				'type'=>'normal',
			),
			array(
				'name'=>'Collie',
				'url'=>'machine/collie',
				'type'=>'normal',
			),
			array(
				'name'=>'Tosa',
				'url'=>'machine/tosa',
				'type'=>'normal',
			)
		)
	),
	array(
		'distro_name'=>'OpenZaurus', 
		'distro_version'=>'3.5.4.1',
		'feed_base_url'=>'http://ewi546.ewi.utwente.nl/mirror/www.openzaurus.org/official/unstable/3.5.4.1/feed/',
		'feeds'=>array(
			array(
				'name'=>'base',
				'url'=>'base',
				'type'=>'normal',
			),
			array(
				'name'=>'opie',
				'url'=>'opie',
				'type'=>'normal',
			),
			array(
				'name'=>'perl',
				'url'=>'perl',
				'type'=>'normal',
			),
			array(
				'name'=>'python',
				'url'=>'python',
				'type'=>'normal',
			),
			array(
				'name'=>'upgrades',
				'url'=>'upgrades',
				'type'=>'upgrades',
			),
			array(
				'name'=>'x11',
				'url'=>'x11',
				'type'=>'normal',
			),
			array(
				'name'=>'C7x0',
				'url'=>'machine/c7x0',
				'type'=>'normal',
			),
			array(
				'name'=>'Spitz',
				'url'=>'machine/spitz',
				'type'=>'normal',
			),
			array(
				'name'=>'Akita',
				'url'=>'machine/akita',
				'type'=>'normal',
			),
			array(
				'name'=>'Akita upgrades',
				'url'=>'upgrades/machine/akita',
				'type'=>'upgrades',
			),
			array(
				'name'=>'C7x0 upgrades',
				'url'=>'upgrades/machine/c7x0',
				'type'=>'upgrades',
			),
			array(
				'name'=>'Spitz upgrades',
				'url'=>'upgrades/machine/spitz',
				'type'=>'upgrades',
			)
		)
	)
);


$ipkpath = "/var/www/angstrom/unstable/feed/unsorted";
$cpu_archs = array('arm-oabi', 'armv4', 'armv4t', 'armv5te', 'armv6', 'armv7', 'avr32', 'bfin', 'i386', 'i486', 'i586', 'i686', 'iwmmxt', 'ppc405', 'ppc603e', 'sparc');
$device_archs = array(
		"a1200" => "armv5te", 
		"a780"=> "armv5te", 
		"acern30", 
		"adsp-bf537-stamp" => "bfin", 
		"adzs-bf538f-ezlite" => "bfin", 
		"akita" => "armv5te", 
		"alix", 
		"amsdelta", 
		"asus620", 
		"asus730", 
		"at32stk1000" => "avr32", 
		"at91sam9260ek" => "armv5te", 
		"at91sam9261ek" => "armv5te", 
		"at91sam9263ek" => "armv5te", 
		"atngw100" => "avr32", 
		"aximx50" => "armv5te", 
		"aximx50v" => "armv5te", 
		"borzoi" => "armv5te", 
		"boxer" => "armv5te", 
		"c7x0" => "armv5te",  
		"colinux", 
		"collie", 
		"compulab-pxa270" => "armv5te", 
		"corgi" => "armv5te", 
		"davinci-dvevm", 
		"db1200", 
		"devkitidp-pxa255" => "armv5te", 
		"dht-walnut" => "ppc405", 
		"e680" => "armv5te", 
		"efika" => "ppc603e", 
		"ep93xx" => "armv4t", 
		"epia", 
		"er0100" => "armv5te", 
		"eteng500" => "armv5te", 
		"fic-gta01" => "armv4t", 
		"fic-gta02" => "armv4t", 
		"fsg3be", 
		"geodegx", 
		"geodelx", 
		"guinness", 
		"gumstix-connex" => "armv5te", 
		"gumstix-verdex" => "armv5te", 
		"h1910", 
		"h1940", 
		"h2200" => "armv5te", 
		"h3600" => "arm-oabi", 
		"h3800" => "arm-oabi", 
		"h3900" => "armv5te", 
		"h4000" => "armv5te", 
		"h5000" => "armv5te", 
		"h6300" => "armv4t", 
		"htcalpine", 
		"htcapache", 
		"htcblueangel", 
		"htchimalaya", 
		"htcsable", 
		"htctornado", 
		"htcuniversal" => "armv5te", 
		"htcwallaby", 
		"husky" => "armv5te", 
		"hx2000" => "armv5te", 
		"hx4700" => "armv5te", 
		"i586-generic" => "i586", 
		"i686-generic" => "i586", 
		"ixp4xx", 
		"ixp4xxbe" => "armv5teb", 
		"ixp4xxle" => "armv5te", 
		"jornada56x", 
		"jornada6xx", 
		"jornada7xx", 
		"kb9202" => "armv4t", 
		"ks8695", 
		"lite5200", 
		"logicpd-pxa270" => "armv5te", 
		"looxc550", 
		"lsarm", 
		"lsppchd", 
		"lsppchg", 
		"magicbox", 
		"magician" => "armv5te", 
		"mainstone" => "armv5te", 
		"mnci", 
		"mtx-1", 
		"mtx-2", 
		"mtx-3", 
		"mtx-3a", 
		"mx21ads" => "armv5te", 
		"mx31ads" => "armv6", 
		"n2100", 
		"native", 
		"navman-icn330", 
		"netbook-pro" => "armv5te", 
		"netvista", 
		"nokia770" => "armv5te", 
		"nokia800", 
		"nslu2" => "armv5te", 
		"nslu2be" => "armv5teb", 
		"nslu2le" => "armv5te", 
		"omap1510inn" => "armv5te", 
		"omap1610h2" => "armv5te", 
		"omap1710h3" => "armv5te", 
		"omap2420h4", 
		"omap2430sdp", 
		"omap5912osk" => "armv5te", 
		"palmld" => "armv5te", 
		"palmt650" => "armv5te", 
		"palmt680", 
		"palmtc", 
		"palmtt", 
		"palmtt2", 
		"palmtt3", 
		"palmtt5", 
		"palmtx", 
		"palmz31", 
		"palmz71", 
		"palmz72", 
		"poodle" => "armv5te", 
		"progear", 
		"qemuarm" => "armv5te", 
		"qemux86", 
		"rb500", 
		"rokre2" => "armv5te", 
		"rokre6" => "armv5te", 
		"rt3000", 
		"rx1950", 
		"rx3000", 
		"sarge-at91" => "armv4t", 
		"sequoia", 
		"shark" => "arm-oabi", 
		"shepherd" => "armv5te", 
		"simpad" => "arm-oabi", 
		"smdk2440" => "armv4t", 
		"smdk2443" => "armv4t", 
		"spitz" => "armv5te", 
		"storcenter", 
		"sun4cdm", 
		"terrier" => "armv5te", 
		"titan", 
		"tosa" => "armv5te", 
		"triton", 
		"ts72xx", 
		"turbostation", 
		"wgt634u", 
		"wl500g", 
		"wrap", 
		"wrt54", 
		"x86-32-nocona", 
		"x86-prescott", 
		"x86-uml", 
		"x86", 
		"xilinx-ml403", 
		"xilinx-ml410", 
		"xxs1500", 
		);

?>		