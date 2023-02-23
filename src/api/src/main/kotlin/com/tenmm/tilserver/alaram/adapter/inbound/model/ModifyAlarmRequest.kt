package com.tenmm.tilserver.alaram.adapter.inbound.model

data class ModifyAlarmRequest(
    val enable: Boolean? = null,
    val iteration: String? = null,
)
