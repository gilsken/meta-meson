# Copyright (C) 2019 Phong LE <ple@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "Wireless packages for s400 board"

inherit packagegroup

WIFI_PKGS = " \
	wpa-supplicant \
	brcmfmac43455-wifi-fw \
"

BT_PKGS = " \
	${@bb.utils.contains("DISTRO_FEATURES", "bluez5", "bluez5", "", d)} \
	bcm4345-bt-fw \
"

RDEPENDS_${PN} = " \
	${BT_PKGS} \
	${@bb.utils.contains("DISTRO_FEATURES", "wifi", "${WIFI_PKGS}", "", d)} \
	${@bb.utils.contains("DISTRO_FEATURES", "bluetooth", "${BT_PKGS}", "", d)} \
"
