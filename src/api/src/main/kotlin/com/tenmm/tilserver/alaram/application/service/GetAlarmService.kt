package com.tenmm.tilserver.alaram.application.service

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.alaram.application.inbound.GetAlarmUseCase
import com.tenmm.tilserver.alaram.application.outbound.GetAlarmPort
import com.tenmm.tilserver.alaram.domain.Alarm
import com.tenmm.tilserver.common.domain.NotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class GetAlarmService(
    private val getAlarmPort: GetAlarmPort
) : GetAlarmUseCase {
    @Transactional(readOnly = true)
    override fun getAlarmByUserId(userIdentifier: Identifier): Alarm {
        return getAlarmPort.findByUserIdentifier(userIdentifier) ?: throw NotFoundException("Alarm not found")
    }
}
