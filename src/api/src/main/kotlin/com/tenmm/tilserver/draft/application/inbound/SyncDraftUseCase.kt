package com.tenmm.tilserver.draft.application.inbound

import com.tenmm.tilserver.common.domain.Identifier

interface SyncDraftUseCase {
    fun syncByUser(userIdentifier: Identifier, data: String)
    fun sync()
}
