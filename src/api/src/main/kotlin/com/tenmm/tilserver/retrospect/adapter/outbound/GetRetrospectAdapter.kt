package com.tenmm.tilserver.retrospect.adapter.outbound

import com.tenmm.tilserver.retrospect.application.outbound.GetRetrospectPort
import com.tenmm.tilserver.outbound.persistence.repository.RetrospectRepository
import com.tenmm.tilserver.outbound.persistence.repository.RetrospectQnaRepository
import com.tenmm.tilserver.outbound.persistence.entity.RetrospectEntity
import com.tenmm.tilserver.outbound.persistence.entity.RetrospectQnaEntity
import com.tenmm.tilserver.common.domain.Identifier
import java.time.LocalDateTime
import java.sql.Timestamp
import org.springframework.stereotype.Component
import java.time.temporal.ChronoUnit

@Component
class GetRetrospectAdapter(
    private val retrospectQnaRepository: RetrospectQnaRepository,
    private val retrospectRepository: RetrospectRepository
) : GetRetrospectPort {
    override fun findOneRetrospectByUserIdentifierToday(userIdentifier: Identifier): List<RetrospectEntity> {
        val currentDateTime = LocalDateTime.now()
        val todayStartDateTime = currentDateTime.toLocalDate().atStartOfDay()
        val todayTimestamp = Timestamp.valueOf(todayStartDateTime)
        val tomorrowStartDateTime = currentDateTime.plus(1, ChronoUnit.DAYS).toLocalDate().atStartOfDay()
        val tomorrowTimestamp = Timestamp.valueOf(tomorrowStartDateTime)
        return retrospectRepository.findOneRetrospectByUserIdentifierToday(userIdentifier.value, todayTimestamp, tomorrowTimestamp)
    }

    override fun findOneRetrospectById(userIdentifier: Identifier): RetrospectEntity? {
        return retrospectRepository.findOneRetrospectById(userIdentifier.value)
    }

    override fun findRetrospectQnaListByRetrospectIdentifier(retrospectIdentifier: Identifier): List<RetrospectQnaEntity> {
        return retrospectQnaRepository.findByRetrospectIdentifier(retrospectIdentifier.value)
    }
}
