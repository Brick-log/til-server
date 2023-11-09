package com.tenmm.tilserver.retrospect.adapter.outbound

import com.tenmm.tilserver.retrospect.application.outbound.GetUserRetrospectPort
import org.springframework.stereotype.Component
import com.tenmm.tilserver.common.domain.Identifier
import java.sql.Timestamp
import com.tenmm.tilserver.outbound.persistence.entity.RetrospectEntity
import com.tenmm.tilserver.outbound.persistence.entity.RetrospectQnaEntity
import com.tenmm.tilserver.outbound.persistence.repository.RetrospectRepository
import com.tenmm.tilserver.outbound.persistence.repository.RetrospectQnaRepository

import com.tenmm.tilserver.common.utils.CryptoHandler

@Component
class GetUserRetrospectAdapter(
    private val cryptoHandler: CryptoHandler,
    private val retrospectRepository: RetrospectRepository,
    private val retrospectQnaRepository: RetrospectQnaRepository
) : GetUserRetrospectPort {
    override fun getRetrospectListByCreatedAt(userIdentifier: Identifier, to: Timestamp, from: Timestamp, pageToken: String): List<RetrospectEntity> {
        return retrospectRepository.findByRetrospectListByUserIdentifierAndTimePeriod(
            userIdentifier = userIdentifier.value,
            to = to,
            from = from,
        )
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

    private fun generatePageToken(
        condition: Boolean,
        lastEntity: RetrospectEntity?,
    ): String? {
        return if (condition) {
            lastEntity!!
            cryptoHandler.encrypt(
                PageTokenSearchRetrospect(
                    lastEntity.id.toInt(),
                    lastEntity.createdAt
                )
            )
        } else {
            null
        }
    }

    private data class PageTokenSearchRetrospect(
        val lastEntityId: Int,
        val lastEntityCreatedAt: Timestamp,
    )
}
