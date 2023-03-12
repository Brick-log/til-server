package com.tenmm.tilserver.alarm.adapter.inbound.rest.model

data class GetAlarmResponse(
    val userIdentifier: String,
    val enable: Boolean,
    val iteration: String

)
