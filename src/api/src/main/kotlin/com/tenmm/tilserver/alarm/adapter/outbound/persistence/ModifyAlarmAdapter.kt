package com.tenmm.tilserver.alarm.adapter.outbound.persistence

import com.tenmm.tilserver.alarm.application.inbound.model.ModifyAlarmCommand
import com.tenmm.tilserver.alarm.application.outbound.ModifyAlarmPort
import com.tenmm.tilserver.common.domain.NotFoundException
import com.tenmm.tilserver.outbound.persistence.repository.AlarmRepository
import mu.KotlinLogging
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

private val logger = KotlinLogging.logger {}

@Component
class ModifyAlarmAdapter(
    private val alarmRepository: AlarmRepository
) : ModifyAlarmPort {
    @Transactional
    override fun modifyByUserIdentifier(command: ModifyAlarmCommand): Boolean {
        val alarmEntity = alarmRepository.findByUserIdentifier(command.userIdentifier.value) ?: throw NotFoundException("Not found ALarm - userIdentifier: ${command.userIdentifier}")
        val modifiedEntity = alarmEntity.copy(
            enable = command.enable,
            iteration = command.iteration
        )

        return try {
            alarmRepository.save(modifiedEntity)
            true
        } catch (e: Exception) {
            logger.error(e) { "Post save Fail - $command" }
            false
        }
    }
}
