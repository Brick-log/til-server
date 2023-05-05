package com.tenmm.tilserver.alarm.domain

// import com.tenmm.tilserver.common.constants.CRON_ITERATION_REGEX
import com.tenmm.tilserver.common.domain.Identifier

data class Alarm(
    val userIdentifier: Identifier,
    val enable: Boolean,
    val iteration: AlarmIteration,
) {
    init {
    }
}
