package com.tenmm.tilserver.draft.application.outbound

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.draft.domain.Draft

interface SyncDraftPort {
    suspend fun save(userIdentifier: Identifier, data: String): Boolean
    suspend fun findByUserIdentifier(userIdentifier: Identifier): Draft?
}
