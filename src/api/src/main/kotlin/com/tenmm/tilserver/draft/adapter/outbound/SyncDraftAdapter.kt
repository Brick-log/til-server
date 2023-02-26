package com.tenmm.tilserver.draft.adapter.outbound

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.draft.application.outbound.SyncDraftPort
import com.tenmm.tilserver.outbound.persistence.repository.DraftRepository
import com.tenmm.tilserver.outbound.persistence.entity.DraftEntity
import java.time.LocalDateTime

class SyncDraftAdapter(
    private val draftSyncRepository: DraftRepository
) : SyncDraftPort {
    override fun syncDraft(userIdentifier: Identifier, data: String) {
        draftSyncRepository.sync(
            DraftEntity(
                userIdentifier = userIdentifier.value,
                data = data,
                updatedAt = LocalDateTime.now()
            )
        )
    }
}
