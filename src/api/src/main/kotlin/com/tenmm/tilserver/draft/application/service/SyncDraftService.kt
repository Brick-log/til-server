package com.tenmm.tilserver.draft.application.service

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.draft.application.inbound.SyncDraftUseCase
import org.springframework.stereotype.Service
import com.tenmm.tilserver.draft.application.outbound.SyncDraftPort
import com.tenmm.tilserver.draft.domain.Draft

@Service
class SyncDraftService(
    private val syncDraftPort: SyncDraftPort,
) : SyncDraftUseCase {
    override fun save(draftIdentifier: Identifier, data: String) {
        syncDraftPort.save(draftIdentifier, data)
    }

    override fun getByUserIdentifier(userIdentifier: Identifier): Draft? {
        return syncDraftPort.getByUserIdentifier(userIdentifier)
    }

    override fun deleteByUserIdentifier(userIdentifier: Identifier) {
        syncDraftPort.deleteByUserIdentifier(userIdentifier)
    }
}
