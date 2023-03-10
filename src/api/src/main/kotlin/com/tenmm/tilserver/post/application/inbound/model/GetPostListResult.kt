package com.tenmm.tilserver.post.application.inbound.model

import com.tenmm.tilserver.post.domain.Post

data class GetPostListResult(
    val posts: List<Post>,
    val size: Int,
    val nextPageToken: String?,
)
