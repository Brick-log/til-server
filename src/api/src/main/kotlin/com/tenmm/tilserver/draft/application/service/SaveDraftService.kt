package com.tenmm.tilserver.draft.application.service

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.draft.application.inbound.SaveDraftUseCase
import com.tenmm.tilserver.draft.application.outbound.SaveDraftPort
import com.tenmm.tilserver.draft.application.outbound.SyncDraftPort
import org.springframework.stereotype.Service

@Service
class SaveDraftService(
    private val saveDraftPort: SaveDraftPort,
    private val syncDraftPort: SyncDraftPort,
) : SaveDraftUseCase {
    override fun saveByUserIdentifier(userIdentifier: Identifier, data: String): Boolean {
        syncDraftPort.deleteById(userIdentifier)
        return saveDraftPort.saveByUserIdentifier(userIdentifier, data) != null
    }
}
