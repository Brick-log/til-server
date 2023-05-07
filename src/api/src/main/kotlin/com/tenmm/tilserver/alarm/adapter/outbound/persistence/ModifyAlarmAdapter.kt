package com.tenmm.tilserver.alarm.adapter.outbound.persistence

import com.tenmm.tilserver.alarm.application.inbound.model.ModifyAlarmCommand
import com.tenmm.tilserver.alarm.application.outbound.ModifyAlarmPort
import com.tenmm.tilserver.outbound.persistence.entity.AlarmEntity
import com.tenmm.tilserver.outbound.persistence.repository.AlarmRepository
import mu.KotlinLogging
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import toEntity

private val logger = KotlinLogging.logger {}

@Component
class ModifyAlarmAdapter(
    private val alarmRepository: AlarmRepository,
) : ModifyAlarmPort {
    @Transactional
    override fun modifyByUserIdentifier(command: ModifyAlarmCommand): Boolean {
        val alarmEntity = alarmRepository.findByUserIdentifier(command.userIdentifier.value)

        val modifiedEntity = alarmEntity?.copy(
            enable = command.enable,
            iteration = command.iteration.toEntity()
        ) ?: AlarmEntity(
            userIdentifier = command.userIdentifier.value,
            enable = command.enable,
            iteration = command.iteration.toEntity()
        )

        return try {
            alarmRepository.save(modifiedEntity)
            true
        } catch (e: Exception) {
            logger.error(e) { "Alarm Modify Fail - $command" }
            false
        }
    }
}
