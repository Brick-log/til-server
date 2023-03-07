package com.tenmm.tilserver.alaram.adapter.inbound.model

data class GetAlarmResponse(
    val userIdentifier: String,
    val enable: Boolean,
    val iteration: String

)
