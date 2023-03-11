package com.tenmm.tilserver.alarm.adapter.outbound.persistence

import com.tenmm.tilserver.alarm.application.outbound.GetAlarmPort
import com.tenmm.tilserver.alarm.domain.Alarm
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
