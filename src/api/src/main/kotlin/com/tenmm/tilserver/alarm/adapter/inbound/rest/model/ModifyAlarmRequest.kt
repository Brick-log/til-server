package com.tenmm.tilserver.alarm.adapter.inbound.rest.model

data class ModifyAlarmRequest(
    val enable: Boolean,
    val iteration: String,
)
