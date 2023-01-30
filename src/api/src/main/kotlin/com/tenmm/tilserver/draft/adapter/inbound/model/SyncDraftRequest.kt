package com.tenmm.tilserver.draft.adapter.inbound.model

data class SyncDraftRequest(
    val draftId: String,
    val data: String,
)
