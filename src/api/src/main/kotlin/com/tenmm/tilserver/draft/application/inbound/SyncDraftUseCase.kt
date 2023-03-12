package com.tenmm.tilserver.draft.application.inbound

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.draft.domain.Draft

interface SyncDraftUseCase {
    fun save(draftIdentifier: Identifier, data: String)
    fun findById(userIdentifier: Identifier): Draft?
    fun findAll(): List<Draft>
    fun deleteById(userIdentifier: Identifier)
}
