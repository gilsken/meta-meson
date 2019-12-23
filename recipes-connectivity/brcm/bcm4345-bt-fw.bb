# Copyright (C) 2019 Phong LE <ple@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "BCM4345 Bluetooth FW files"
LICENSE = "CLOSED"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI = " \
	file://BCM4345C0.hcd \
	file://rfcomm.conf \
"

do_install() {
	if ${@bb.utils.contains('DISTRO_FEATURES','systemd','true','false',d)}; then
		install -d ${D}/etc/modules-load.d
		install -m 0644 ${WORKDIR}/rfcomm.conf ${D}/etc/modules-load.d
	else
		install -d ${D}${sysconfdir}/modprobe.d
		install -m 0644 ${WORKDIR}/rfcomm-mod.conf ${D}${sysconfdir}/modprobe.d/
	fi
	install -d ${D}${nonarch_base_libdir}/firmware/brcm
	install -m 0644 ${WORKDIR}/BCM4345C0.hcd ${D}${nonarch_base_libdir}/firmware/brcm/
}

FILES_${PN} += "/etc/modules-load.d/ ${nonarch_base_libdir}/firmware/brcm/"
