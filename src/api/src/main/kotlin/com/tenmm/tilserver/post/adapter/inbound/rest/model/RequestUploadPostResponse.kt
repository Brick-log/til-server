package com.tenmm.tilserver.post.adapter.inbound.rest.model

import com.tenmm.tilserver.post.application.inbound.model.PostSaveRequestResult
import java.sql.Timestamp

data class RequestUploadPostResponse(
    val url: String,
    val title: String,
    val summary: String?,
    val createdAt: Timestamp?,
) {
    companion object {
        fun fromResult(result: PostSaveRequestResult): RequestUploadPostResponse {
            return RequestUploadPostResponse(
                url = result.url.value,
                title = result.title,
                summary = result.description,
                createdAt = result.createdAt,
            )
        }
    }
}
