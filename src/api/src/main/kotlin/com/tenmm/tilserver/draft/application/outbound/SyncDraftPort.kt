package com.tenmm.tilserver.draft.application.outbound

import com.tenmm.tilserver.common.domain.Identifier

interface SyncDraftPort {
    fun syncDraft(userIdentifier: Identifier, data: String)
}
