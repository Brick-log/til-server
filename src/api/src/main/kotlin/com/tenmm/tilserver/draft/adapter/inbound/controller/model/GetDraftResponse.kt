package com.tenmm.tilserver.draft.adapter.inbound.controller.model

// swagger
import io.swagger.v3.oas.annotations.media.Schema

data class GetDraftResponse(
    @Schema(description = "저잗된 draft data", example = "저잗된 draft data")
    val data: String,
    @Schema(description = "draft가 저장된 시간", example = "2021-08-01T00:00:00.000")
    val updatedAt: Long,
)
