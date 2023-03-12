package com.tenmm.tilserver.draft.adapter.outbound

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.draft.application.outbound.SyncDraftPort
import com.tenmm.tilserver.outbound.persistence.repository.DraftSyncRepository
import com.tenmm.tilserver.outbound.persistence.entity.DraftSyncEntity
import com.tenmm.tilserver.draft.domain.Draft
import java.time.LocalDateTime
import org.springframework.stereotype.Component
import java.sql.Timestamp
import java.util.Optional

@Component
class SyncDraftAdapter(
    private val draftSyncRepository: DraftSyncRepository
) : SyncDraftPort {
    override fun save(userIdentifier: Identifier, data: String) {
        draftSyncRepository.save(
            DraftSyncEntity(
                userIdentifier = userIdentifier.value,
                data = data,
                updatedAt = LocalDateTime.now()
            )
        )
    }
    override fun findById(userIdentifier: Identifier): Draft? {
        val draftSyncEntity: Optional<DraftSyncEntity> = draftSyncRepository.findById(userIdentifier.value)
        return draftSyncEntity.map {
            Draft(
                userIdentifier = Identifier(it.userIdentifier),
                data = it.data,
                updatedAt = Timestamp.valueOf(it.updatedAt)
            )
        }.orElse(null)
    }
    override fun deleteById(userIdentifier: Identifier) {
        draftSyncRepository.deleteById(userIdentifier.value)
    }

    override fun findAll(): List<Draft> {
        return draftSyncRepository.findAll().map {
            Draft(
                userIdentifier = Identifier(it.userIdentifier),
                data = it.data,
                updatedAt = Timestamp.valueOf(it.updatedAt)
            )
        }
    }
}
