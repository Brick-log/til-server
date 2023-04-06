package com.tenmm.tilserver.draft.adapter.inbound.controller.model

import com.tenmm.tilserver.draft.domain.Draft
import java.sql.Timestamp

data class GetDraftResponse(
    val data: String?,
    val updatedAt: Timestamp?,
) {
    companion object {
        val EMPTY = GetDraftResponse(null, null)
    }
}

fun Draft?.toResponse(): GetDraftResponse {
    return this?.let {
        GetDraftResponse(
            data = data,
            updatedAt = updatedAt
        )
    } ?: GetDraftResponse.EMPTY
}
