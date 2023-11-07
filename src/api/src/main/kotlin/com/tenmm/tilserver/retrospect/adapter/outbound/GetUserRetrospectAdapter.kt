package com.tenmm.tilserver.retrospect.adapter.outbound

import com.tenmm.tilserver.retrospect.application.outbound.GetUserRetrospectPort
import org.springframework.stereotype.Component

import java.sql.Timestamp
import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.outbound.persistence.entity.RetrospectEntity
import com.tenmm.tilserver.outbound.persistence.entity.RetrospectQnaEntity
import com.tenmm.tilserver.outbound.persistence.repository.RetrospectRepository
import com.tenmm.tilserver.outbound.persistence.repository.RetrospectQnaRepository

@Component
class GetUserRetrospectAdapter(
    private val retrospectRepository: RetrospectRepository,
    private val retrospectQnaRepository: RetrospectQnaRepository
) : GetUserRetrospectPort {
    override fun getRetrospectListByCreatedAt(userIdentifier: Identifier, to: Timestamp, from: Timestamp, isSecret: Boolean): List<RetrospectEntity> {
        return if (isSecret == false) {
            retrospectRepository.findByRetrospectListByUserIdentifierAndTimePeriodAndSecretIsFalse(
                userIdentifier = userIdentifier.value,
                to = to,
                from = from,
            )
        } else {
            retrospectRepository.findByRetrospectListByUserIdentifierAndTimePeriod(
                userIdentifier = userIdentifier.value,
                to = to,
                from = from,
            )
        }
    }

    override fun totalRetrospectCountByUser(userIdentifier: Identifier): Int {
        return retrospectRepository.countAllByUserIdentifier(
            userIdentifier = userIdentifier.value
        )
    }

    override fun getRetrospectListByRetrospectIdentifier(retrospectIdentifier: Identifier): List<RetrospectQnaEntity> {
        return retrospectQnaRepository.findByRetrospectIdentifier(
            retrospectIdentifier = retrospectIdentifier.value
        )
    }
}
