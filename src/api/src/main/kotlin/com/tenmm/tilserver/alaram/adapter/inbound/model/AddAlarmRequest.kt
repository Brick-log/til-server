package com.tenmm.tilserver.alaram.adapter.inbound.model

data class AddAlarmRequest(
    val enable: Boolean,
    val iteration: String,
)
