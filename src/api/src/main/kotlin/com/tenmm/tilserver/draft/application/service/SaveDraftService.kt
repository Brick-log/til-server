package com.tenmm.tilserver.draft.application.service

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.draft.application.inbound.SaveDraftUseCase

import org.springframework.stereotype.Service
import java.sql.Timestamp

@Service
class SaveDraftService(
    private val saveDraftPort: SaveDraftPort,
) : SaveDraftUseCase {
    override fun save(userIdentifier: Identifier, data: String) {
        saveDraftPort.saveDraft(userIdentifier, data)
        TODO("Not yet implemented")
    }
}
