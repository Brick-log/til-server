package com.tenmm.tilserver.draft.adapter.inbound.model

data class GetDraftResponse(
    val id: String,
    val data: String,
    val updatedAt: Long,
)
