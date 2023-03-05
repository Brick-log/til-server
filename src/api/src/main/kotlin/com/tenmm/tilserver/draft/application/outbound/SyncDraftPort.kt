package com.tenmm.tilserver.draft.application.outbound

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.draft.domain.Draft

interface SyncDraftPort {
    fun save(userIdentifier: Identifier, data: String)
    fun getByUserIdentifier(userIdentifier: Identifier): Draft?
    fun deleteByUserIdentifier(userIdentifier: Identifier)
}
