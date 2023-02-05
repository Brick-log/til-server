package com.tenmm.tilserver.post.adapter.inbound.model

import com.tenmm.tilserver.post.domain.Post

data class GetPostListResponse(
    val postList: List<Post>,
    val nextPageToken: String,
    val prevPageToken: String,
)
