package com.tenmm.tilserver.alarm.adapter.inbound.rest.model

fun randomModifyAlarmRequest(isEnable: Boolean = true): ModifyAlarmRequest {
    return ModifyAlarmRequest(
        enable = isEnable,
        iteration = "* * * * * *"
    )
}
