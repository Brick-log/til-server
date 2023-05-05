package com.tenmm.tilserver.alarm.application.inbound.model

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.alarm.domain.AlarmIteration

data class ModifyAlarmCommand(
    val userIdentifier: Identifier,
    val enable: Boolean,
    val iteration: AlarmIteration,
)
