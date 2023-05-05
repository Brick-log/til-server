package com.tenmm.tilserver.draft.application.service

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.draft.application.inbound.SaveDraftUseCase
import com.tenmm.tilserver.draft.application.outbound.SaveDraftPort
import com.tenmm.tilserver.draft.application.outbound.SyncDraftPort
import com.tenmm.tilserver.draft.domain.Draft
import org.springframework.stereotype.Service

@Service
class SaveDraftService(
    private val saveDraftPort: SaveDraftPort,
    private val syncDraftPort: SyncDraftPort,
) : SaveDraftUseCase {
    override suspend fun saveByUserIdentifier(userIdentifier: Identifier, data: String): Boolean {
        val draft = Draft(
            data = data,
            userIdentifier  = userIdentifier

        )
        val isSyncSuccess = saveDraftPort.upsert(draft)

        if (isSyncSuccess) {
            return syncDraftPort.delete(draft)
        }
        return false
    }
}
