package com.tenmm.tilserver.draft.adapter.outbound

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.common.utils.getNowTimestamp
import com.tenmm.tilserver.draft.application.outbound.SyncDraftPort
import com.tenmm.tilserver.draft.domain.Draft
import com.tenmm.tilserver.outbound.persistence.entity.DraftEntity
import com.tenmm.tilserver.outbound.persistence.repository.DraftRepository
import mu.KotlinLogging
import org.springframework.stereotype.Component

private val logger = KotlinLogging.logger {}
@Component
class SyncDraftAdapter(
    private val draftRepository: DraftRepository,
) : SyncDraftPort {
    override suspend fun save(userIdentifier: Identifier, data: String): Boolean {
        return try {
            val entity = draftRepository.findByUserIdentifier(userIdentifier = userIdentifier.value)
            draftRepository.save(
                DraftEntity(
                    id = entity?.id ?: 0,
                    userIdentifier = userIdentifier.value,
                    data = data,
                    updatedAt = getNowTimestamp()
                )
            )
            true
        } catch (e: Exception) {
            false
        }
    }

    override suspend fun findByUserIdentifier(userIdentifier: Identifier): Draft? {
        return this.draftRepository.findByUserIdentifier(userIdentifier.value)?.let {
            Draft(
                userIdentifier = Identifier(it.userIdentifier),
                data = it.data
            )
        }
    }
}
