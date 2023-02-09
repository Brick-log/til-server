package com.tenmm.tilserver.draft.application.inbound

import com.tenmm.tilserver.common.domain.Identifier

interface SyncDraftUseCase {
    fun sync(draftIdentifier: Identifier, data: String)
}
