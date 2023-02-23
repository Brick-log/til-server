package com.tenmm.tilserver.post.adapter.inbound.rest.model

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.post.application.inbound.model.PostSaveRequestResult
import java.sql.Timestamp

data class RequestUploadPostResponse(
    val saveIdentifier: Identifier,
    val title: String,
    val description: String?,
    val createdAt: Timestamp?,
) {
    companion object {
        fun fromResult(result: PostSaveRequestResult): RequestUploadPostResponse {
            return RequestUploadPostResponse(
                saveIdentifier = result.saveIdentifier,
                title = result.title,
                description = result.description,
                createdAt = result.createdAt,
            )
        }
    }
}
