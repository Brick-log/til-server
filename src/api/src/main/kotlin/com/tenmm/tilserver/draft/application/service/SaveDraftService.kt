package com.tenmm.tilserver.draft.application.service

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.draft.domain.Draft
import com.tenmm.tilserver.draft.application.inbound.SaveDraftUseCase
import com.tenmm.tilserver.draft.application.outbound.SaveDraftPort
import org.springframework.stereotype.Service

@Service
class SaveDraftService(
    private val saveDraftPort: SaveDraftPort,
) : SaveDraftUseCase {
    override fun saveByUserIdentifier(userIdentifier: Identifier, data: String): Draft? {
        return saveDraftPort.saveByUserIdentifier(userIdentifier, data)
    }
}
