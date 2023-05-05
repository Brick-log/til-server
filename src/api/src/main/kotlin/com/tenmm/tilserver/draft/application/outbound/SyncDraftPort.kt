package com.tenmm.tilserver.draft.application.outbound

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.draft.domain.Draft

interface SyncDraftPort {
    suspend fun save(userIdentifier: Identifier, data: String): Boolean
    suspend fun findByUserIdentifier(userIdentifier: Identifier): Draft?
    suspend fun findDraftsWithCount(size: Int): List<Draft>
    suspend fun delete(draft: Draft): Boolean
    suspend fun delete(drafts: List<Draft>): Boolean
}
