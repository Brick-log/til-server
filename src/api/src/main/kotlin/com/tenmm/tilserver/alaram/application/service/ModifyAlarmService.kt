package com.tenmm.tilserver.alaram.application.service

import com.tenmm.tilserver.alaram.application.inbound.model.ModifyAlarmCommand
import com.tenmm.tilserver.alaram.application.inbound.ModifyAlarmUseCase
import com.tenmm.tilserver.alaram.application.outbound.GetAlarmPort
import com.tenmm.tilserver.common.domain.NotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ModifyAlarmService(
    private val getAlarmPort: GetAlarmPort
) : ModifyAlarmUseCase {
    @Transactional
    override fun modifyAlarm(alarm: ModifyAlarmCommand): Boolean {
        val alarm = getAlarmPort.findByUserIdentifier(alarm.userIdentifier) ?: throw NotFoundException("Alarm Not Found")
        return true
    }
}
