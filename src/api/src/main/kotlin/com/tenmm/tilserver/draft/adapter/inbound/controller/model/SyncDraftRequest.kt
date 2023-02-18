package com.tenmm.tilserver.draft.adapter.inbound.controller.model

import com.tenmm.tilserver.common.domain.Identifier

data class SyncDraftRequest(
    val draftId: Identifier,
    val data: String,
)
