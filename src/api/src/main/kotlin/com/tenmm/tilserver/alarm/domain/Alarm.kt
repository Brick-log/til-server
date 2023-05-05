package com.tenmm.tilserver.alarm.domain

import com.tenmm.tilserver.common.domain.Identifier

data class Alarm(
    val userIdentifier: Identifier,
    val enable: Boolean,
    val iteration: AlarmIteration,
) {
    companion object {
        fun empty(userIdentifier: Identifier): Alarm {
            return Alarm(userIdentifier, false, AlarmIteration.NONE)
        }
    }
}
