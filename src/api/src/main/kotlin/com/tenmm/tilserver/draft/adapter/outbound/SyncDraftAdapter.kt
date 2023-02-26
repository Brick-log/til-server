package com.tenmm.tilserver.draft.adapter.outbound

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.draft.application.outbound.SyncDraftPort
import com.tenmm.tilserver.outbound.persistence.repository.DraftSyncRepository
import com.tenmm.tilserver.outbound.persistence.entity.DraftSyncEntity
import java.time.LocalDateTime
import org.springframework.stereotype.Component

@Component
class SyncDraftAdapter(
    private val draftSyncRepository: DraftSyncRepository
) : SyncDraftPort {
    override fun syncDraft(userIdentifier: Identifier, data: String) {
        draftSyncRepository.save(
            DraftSyncEntity(
                userIdentifier = userIdentifier.value,
                data = data,
                updatedAt = LocalDateTime.now()
            )
        )
    }
}
