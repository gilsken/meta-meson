# Copyright (C) 2019 Phong Le <ple@baylibre.com>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "Broadcom Wi-Fi FW files"
LICENSE = "CLOSED"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI = " \
	file://brcmfmac43455-sdio.bin \
	file://brcmfmac43455-sdio.clm_blob \
	file://brcmfmac43455-sdio.txt \
"

do_install() {
	install -d ${D}${nonarch_base_libdir}/firmware/brcm
	install -m 0644 ${WORKDIR}/brcmfmac43455-sdio.bin ${D}${nonarch_base_libdir}/firmware/brcm/
	install -m 0644 ${WORKDIR}/brcmfmac43455-sdio.clm_blob ${D}${nonarch_base_libdir}/firmware/brcm/
	install -m 0644 ${WORKDIR}/brcmfmac43455-sdio.txt ${D}${nonarch_base_libdir}/firmware/brcm/brcmfmac43455-sdio.amlogic,s400.txt
}

FILES_${PN} += "${nonarch_base_libdir}/firmware/brcm/"
