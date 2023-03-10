package com.tenmm.tilserver.alaram.application.inbound.model

import com.tenmm.tilserver.common.domain.Identifier

data class ModifyAlarmCommand(
    val userIdentifier: Identifier,
    val enable: Boolean,
    val iteration: String,
)
