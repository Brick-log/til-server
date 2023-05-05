package com.tenmm.tilserver.alarm.adapter.inbound.rest.model

import com.tenmm.tilserver.alarm.domain.AlarmIteration

fun randomModifyAlarmRequest(isEnable: Boolean = true): ModifyAlarmRequest {
    return ModifyAlarmRequest(
        enable = isEnable,
        iteration = AlarmIteration.valueOf("DAY")
        // iteration = "* * * * * *"
    )
}
