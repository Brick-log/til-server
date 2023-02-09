package com.tenmm.tilserver.post.adapter.inbound.model

import com.tenmm.tilserver.common.domain.Url
import com.tenmm.tilserver.post.application.inbound.model.GetPostResult

data class GetPostResponse(
    val url: Url,
) {
    companion object {
        fun fromResult(result: GetPostResult): GetPostResponse {
            return GetPostResponse(url = result.url)
        }
    }
}
