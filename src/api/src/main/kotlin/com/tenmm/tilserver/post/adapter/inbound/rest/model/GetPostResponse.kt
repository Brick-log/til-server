package com.tenmm.tilserver.post.adapter.inbound.rest.model

import com.tenmm.tilserver.post.application.inbound.model.GetPostResult

data class GetPostResponse(
    val url: String,
) {
    companion object {
        fun fromResult(result: GetPostResult): GetPostResponse {
            return GetPostResponse(url = result.url.value)
        }
    }
}
