package com.tenmm.tilserver.draft.application.inbound

import com.tenmm.tilserver.common.domain.Identifier

interface SyncDraftUseCase {
    suspend fun syncByUser(userIdentifier: Identifier, data: String): Boolean
}
