package com.tenmm.tilserver.alarm.adapter.outbound.persistence.model

import com.tenmm.tilserver.alarm.application.inbound.model.ModifyAlarmCommand
import com.tenmm.tilserver.alarm.domain.AlarmIteration
import com.tenmm.tilserver.common.domain.Identifier

fun randomModifyAlarmCommand(userIdentifier: Identifier = Identifier.generate(), isEnable: Boolean = true): ModifyAlarmCommand {
    return ModifyAlarmCommand(
        userIdentifier = userIdentifier,
        enable = isEnable,
        iteration = AlarmIteration.valueOf("DAY")
    )
}
