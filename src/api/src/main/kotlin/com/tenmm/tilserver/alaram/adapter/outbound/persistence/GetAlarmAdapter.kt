package com.tenmm.tilserver.alaram.adapter.outbound.persistence

import com.tenmm.tilserver.alaram.application.outbound.GetAlarmPort
import com.tenmm.tilserver.alaram.domain.Alarm
import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.outbound.persistence.repository.AlarmRepository
import org.springframework.stereotype.Component
import toModel

@Component
class GetAlarmAdapter(
    private val alarmRepository: AlarmRepository
) : GetAlarmPort {
    override fun findByUserIdentifier(userIdentifier: Identifier): Alarm? {
        return alarmRepository.findByUserIdentifier(userIdentifier.toString())?.toModel()
    }
}
