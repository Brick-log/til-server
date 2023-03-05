package com.tenmm.tilserver.draft.application.inbound

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.draft.domain.Draft

interface SyncDraftUseCase {
    fun save(draftIdentifier: Identifier, data: String)
    fun getByUserIdentifier(userIdentifier: Identifier): Draft?
    fun deleteByUserIdentifier(userIdentifier: Identifier)
}
