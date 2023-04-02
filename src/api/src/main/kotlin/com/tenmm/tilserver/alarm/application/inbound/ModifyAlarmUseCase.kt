package com.tenmm.tilserver.alarm.application.inbound

import com.tenmm.tilserver.alarm.application.inbound.model.ModifyAlarmCommand
import com.tenmm.tilserver.common.domain.OperationResult

interface ModifyAlarmUseCase {
    fun modifyAlarm(command: ModifyAlarmCommand): OperationResult
}
