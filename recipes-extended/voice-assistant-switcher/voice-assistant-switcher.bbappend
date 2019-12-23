FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += " \
	file://tasks-s400.ini \
	file://override_vad.conf \
	file://vad.timer \
"

do_install_append() {
	install -d ${D}${systemd_unitdir}/system/

	install -d 0755 ${D}${sysconfdir}/systemd/system/vad.service.d
	install -m 0664 ${WORKDIR}/override_vad.conf ${D}${sysconfdir}/systemd/system/vad.service.d/override.conf

	install -m 0664 ${WORKDIR}/tasks-s400.ini ${D}${sysconfdir}

	install -m  0664 ${WORKDIR}/vad.timer ${D}${systemd_unitdir}/system/
	install -d ${D}${sysconfdir}/systemd/system/timers.target.wants/
	ln -sf ${systemd_unitdir}/system/vad.timer \
		${D}${sysconfdir}/systemd/system/timers.target.wants/vad.timer

}

FILES_${PN} += "\
	/lib \
	/lib/systemd \
	/lib/systemd/system \
	/lib/systemd/system/* \
	${sysconfdir}/tasks-s400.ini \
	${sysconfdir}/systemd/system/vad.service.d/override.conf \
"
