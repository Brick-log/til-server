package com.tenmm.tilserver.draft.application.service

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.draft.application.inbound.SyncDraftUseCase
import com.tenmm.tilserver.draft.application.outbound.SaveDraftPort
import com.tenmm.tilserver.draft.application.outbound.SyncDraftPort
import org.springframework.stereotype.Service

private const val GET_DRAFT_COUNT_PER_ONCE = 50

@Service
class SyncDraftService(
    private val saveDraftPort: SaveDraftPort,
    private val syncDraftPort: SyncDraftPort,
) : SyncDraftUseCase {
    override fun syncByUser(userIdentifier: Identifier, data: String) {
        syncDraftPort.save(userIdentifier, data)
    }

    override fun sync() {
        syncDraftPort.findDraftsWithCount(GET_DRAFT_COUNT_PER_ONCE).forEach {
            saveDraftPort.saveByUserIdentifier(it.userIdentifier, it.data)
            syncDraftPort.deleteById(it.userIdentifier)
        }
    }
}
