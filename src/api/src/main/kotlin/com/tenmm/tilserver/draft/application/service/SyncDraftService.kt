package com.tenmm.tilserver.draft.application.service

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.draft.application.inbound.SyncDraftUseCase
import org.springframework.stereotype.Service

@Service
class SyncDraftService : SyncDraftUseCase {
    override fun sync(draftIdentifier: Identifier, data: String) {
        TODO("Not yet implemented")
    }
}
