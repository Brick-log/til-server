package com.tenmm.tilserver.alaram.adapter.inbound.model

data class ModifyAlarmRequest(
    val name: String,
    val description: String,
    val iteration: String,
)
