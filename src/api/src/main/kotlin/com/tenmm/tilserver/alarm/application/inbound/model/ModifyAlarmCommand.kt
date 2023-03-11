package com.tenmm.tilserver.alarm.application.inbound.model

import com.tenmm.tilserver.common.domain.Identifier

data class ModifyAlarmCommand(
    val userIdentifier: Identifier,
    val enable: Boolean,
    val iteration: String,
)
