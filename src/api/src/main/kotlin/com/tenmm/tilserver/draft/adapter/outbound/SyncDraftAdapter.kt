package com.tenmm.tilserver.draft.adapter.outbound

import com.tenmm.tilserver.draft.application.outbound.syncDraftPort
import com.tenmm.tilserver.draft.adapter.outbound.persistence.DraftSyncRepository
import com.tenmm.tilserver.draft.adapter.outbound.persistence.model.DraftSyncEntity

import java.sql.Timestamp

class SyncDraftAdapter(
    private val draftSyncRepository: DraftSyncRepository
) :SyncDraftPort {
    fun syncDraft(userIdentifier: Identifier, data: String) {
        draftSyncRepository.syncDraft(
            DraftSyncEntity(
                userIdentifier = userIdentifier,
                data = data
            )
        )
    }
}