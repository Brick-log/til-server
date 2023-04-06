package com.tenmm.tilserver.alarm.application.service

import com.tenmm.tilserver.alarm.application.inbound.ModifyAlarmUseCase
import com.tenmm.tilserver.alarm.application.inbound.model.ModifyAlarmCommand
import com.tenmm.tilserver.alarm.application.outbound.ModifyAlarmPort
import com.tenmm.tilserver.common.domain.OperationResult
import org.springframework.stereotype.Service

@Service
class ModifyAlarmService(
    private val modifyAlarmPort: ModifyAlarmPort,
) : ModifyAlarmUseCase {
    override fun modifyAlarm(command: ModifyAlarmCommand): OperationResult {
        return OperationResult(modifyAlarmPort.modifyByUserIdentifier(command))
    }
}
