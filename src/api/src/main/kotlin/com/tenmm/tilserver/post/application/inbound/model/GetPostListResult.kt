package com.tenmm.tilserver.post.application.inbound.model

import com.tenmm.tilserver.post.domain.PostWithUserPath

data class GetPostListResult(
    val posts: List<PostWithUserPath>,
    val size: Int,
    val nextPageToken: String?,
)
