FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append = " \
	file://default.pa \
"

do_install_append() {
	install -d ${D}${sysconfdir}/pulse
	install -m 0644 ${WORKDIR}/default.pa ${D}${sysconfdir}/pulse/default.pa
}

