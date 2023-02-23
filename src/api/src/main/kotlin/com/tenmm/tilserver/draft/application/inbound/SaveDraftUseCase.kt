package com.tenmm.tilserver.draft.application.inbound

import com.tenmm.tilserver.common.domain.Identifier

interface SaveDraftUseCase {
    fun save(userIdentifier: Identifier, data: String)
}
