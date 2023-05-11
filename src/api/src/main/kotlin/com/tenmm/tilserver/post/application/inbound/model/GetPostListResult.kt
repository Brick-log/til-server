package com.tenmm.tilserver.post.application.inbound.model

import com.tenmm.tilserver.post.domain.PostDetail

data class GetPostListResult(
    val posts: List<PostDetail>,
    val size: Int,
    val nextPageToken: String?,
)
