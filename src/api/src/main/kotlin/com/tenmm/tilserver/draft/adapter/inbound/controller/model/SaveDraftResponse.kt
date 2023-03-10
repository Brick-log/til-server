package com.tenmm.tilserver.draft.adapter.inbound.controller.model

// swagger
import io.swagger.v3.oas.annotations.media.Schema

data class SaveDraftResponse(
    @Schema(description = "draft 저장 성공 여부", example = "true")
    val isSuccess: Boolean
)
