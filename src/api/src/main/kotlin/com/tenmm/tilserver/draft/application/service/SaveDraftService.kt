package com.tenmm.tilserver.draft.application.service

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.draft.application.inbound.SaveDraftUseCase
import org.springframework.stereotype.Service

@Service
class SaveDraftService : SaveDraftUseCase {
    override fun save(draftIdentifier: Identifier, data: String) {
        TODO("Not yet implemented")
    }

    override fun saveAll() {
        TODO("Not yet implemented")
    }
}
