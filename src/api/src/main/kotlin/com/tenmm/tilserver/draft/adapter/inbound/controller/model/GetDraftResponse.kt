package com.tenmm.tilserver.draft.adapter.inbound.controller.model

import java.sql.Timestamp

data class GetDraftResponse(
    val data: String?,
    val updatedAt: Timestamp?,
)
