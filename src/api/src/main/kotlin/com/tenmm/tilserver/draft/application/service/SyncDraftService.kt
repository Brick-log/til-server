package com.tenmm.tilserver.draft.application.service

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.draft.application.inbound.SyncDraftUseCase
import org.springframework.stereotype.Service
import com.tenmm.tilserver.draft.application.outbound.SyncDraftPort

@Service
class SyncDraftService(
    private val syncDraftPort: SyncDraftPort,
) : SyncDraftUseCase {
    override fun sync(draftIdentifier: Identifier, data: String) {
        syncDraftPort.syncDraft(draftIdentifier, data)
    }
}
