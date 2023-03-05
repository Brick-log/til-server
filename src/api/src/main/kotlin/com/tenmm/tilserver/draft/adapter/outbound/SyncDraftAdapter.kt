package com.tenmm.tilserver.draft.adapter.outbound

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.draft.application.outbound.SyncDraftPort
import com.tenmm.tilserver.outbound.persistence.repository.DraftSyncRepository
import com.tenmm.tilserver.outbound.persistence.entity.DraftSyncEntity
import com.tenmm.tilserver.draft.domain.Draft
import java.time.LocalDateTime
import org.springframework.stereotype.Component
import java.sql.Timestamp

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
    override fun getByUserIdentifier(userIdentifier: Identifier): Draft? {
        val draftSyncEntity: DraftSyncEntity? = draftSyncRepository.findByUserIdentifier(userIdentifier.value)
        draftSyncEntity?.let {
            return Draft(
                userIdentifier = Identifier(it.userIdentifier),
                data = it.data,
                updatedAt = Timestamp.valueOf(it.updatedAt)
            )
        } ?: return null
    }
    override fun deleteByUserIdentifier(userIdentifier: Identifier) {
        draftSyncRepository.deleteByUserIdentifier(userIdentifier.value)
    }
}
