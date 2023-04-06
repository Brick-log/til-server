package com.tenmm.tilserver.draft.application.inbound

import com.tenmm.tilserver.common.domain.Identifier

interface SaveDraftUseCase {
    fun saveByUserIdentifier(
        userIdentifier: Identifier,
        data: String,
    ): Boolean
}
