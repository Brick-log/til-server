package com.tenmm.tilserver.draft.application.inbound

import com.tenmm.tilserver.common.domain.Identifier

interface SaveDraftUseCase {
    suspend fun saveByUserIdentifier(
        userIdentifier: Identifier,
        data: String,
    ): Boolean
}
