package com.tenmm.tilserver.post.adapter.inbound.rest.model

import com.tenmm.tilserver.post.application.inbound.model.GetPostListResult
import com.tenmm.tilserver.post.domain.Post

data class GetPostListResponse(
    val postList: List<Post>,
    val size: Int,
    val nextPageToken: String,
) {
    companion object {
        fun fromResult(result: GetPostListResult): GetPostListResponse {
            return GetPostListResponse(
                postList = result.posts,
                size = result.size,
                nextPageToken = result.nextPageToken,
            )
        }
    }
}
