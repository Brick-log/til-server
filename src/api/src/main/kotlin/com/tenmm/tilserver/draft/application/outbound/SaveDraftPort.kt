package com.tenmm.tilserver.draft.application.outbound

import com.tenmm.tilserver.common.domain.Identifier

interface SaveDraftPort {
    fun saveDraft(userIdentifier: Identifier, data: String)
}
