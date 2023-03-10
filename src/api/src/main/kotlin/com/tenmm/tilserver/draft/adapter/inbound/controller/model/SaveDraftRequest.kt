package com.tenmm.tilserver.draft.adapter.inbound.controller.model

// swagger
import io.swagger.v3.oas.annotations.media.Schema

data class SaveDraftRequest(
    @Schema(description = "저장할 draft data", example = "저장할 draft data", required = true)
    val data: String,
)
