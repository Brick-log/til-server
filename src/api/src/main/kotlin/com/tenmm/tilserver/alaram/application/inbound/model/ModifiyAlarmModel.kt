package com.tenmm.tilserver.alaram.application.inbound.model

import com.tenmm.tilserver.common.domain.Identifier

data class ModifiyAlarmModel(
    val userIdentifier: Identifier,
    val enable: Boolean? = null,
    val iteration: String? = null,
)
