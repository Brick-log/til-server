package com.tenmm.tilserver.draft.adapter.inbound.controller.model

data class SyncDraftRequest(
    val draftId: String,
    val data: String,
)
