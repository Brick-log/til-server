package com.tenmm.tilserver.alarm.adapter.inbound.rest.model

import com.tenmm.tilserver.alarm.domain.AlarmIteration

data class GetAlarmResponse(
    val enable: Boolean,
    val iteration: AlarmIteration
)
