package com.tenmm.tilserver.draft.application.outbound

import com.tenmm.tilserver.draft.domain.Draft

interface SaveDraftPort {
    fun upsert(draft: Draft): Boolean
}
