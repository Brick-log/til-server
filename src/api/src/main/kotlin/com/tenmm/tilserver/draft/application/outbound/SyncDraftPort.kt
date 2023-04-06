package com.tenmm.tilserver.draft.application.outbound

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.draft.domain.Draft

interface SyncDraftPort {
    fun save(userIdentifier: Identifier, data: String)
    fun findByUserIdentifier(userIdentifier: Identifier): Draft?
    fun findDraftsWithCount(size: Int): List<Draft>
    fun deleteById(userIdentifier: Identifier): Boolean
}
