FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

inherit systemd

USB_GADGET_FUNCTION ?= "ecm"

SRC_URI_append = " \
	file://udhcpd.conf \
	file://eth.network \
	file://wireless.network \
	file://brcmfmac43455.conf \
"

do_install_append() {
	install -d ${D}${systemd_unitdir}/system
	install -d ${D}${sysconfdir}/init.d

	install -d ${D}${sysconfdir}/systemd/network/
	install -m 0644 ${WORKDIR}/eth.network ${D}${sysconfdir}/systemd/network/
	install -m 0644 ${WORKDIR}/wireless.network ${D}${sysconfdir}/systemd/network/

	install -m 0644 ${WORKDIR}/udhcpd.conf ${D}${sysconfdir}/udhcpd.conf

	if [ "${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'systemd', 'sysvinit', d)}" = "sysvinit" ]; then
		install -d ${D}${sysconfdir}/modprobe.d
		install -m 0644 ${WORKDIR}/brcmfmac43455.conf ${D}${sysconfdir}/modprobe.d/
	fi
}

SYSTEMD_PACKAGES = "${@bb.utils.contains('DISTRO_FEATURES', 'systemd', '${PN}', '', d)}"
FILES_${PN} += " \
	${sysconfdir}/systemd/network/eth.network \
	${sysconfdir}/systemd/network/wireless.network \
	${sysconfdir}/udhcpd.conf \
	${sysconfdir}/modprobe.d/brcmfmac43455.conf \
"
