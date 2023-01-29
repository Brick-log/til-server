package com.tenmm.tilserver.alaram.adapter.inbound.model

data class AddAlarmRequest(
    val name: String,
    val description: String,
    val iteration: String,
)
