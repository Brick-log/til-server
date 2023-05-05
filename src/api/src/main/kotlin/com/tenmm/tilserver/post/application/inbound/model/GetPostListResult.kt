package com.tenmm.tilserver.post.application.inbound.model

import com.tenmm.tilserver.post.domain.PostWithUserInfo

data class GetPostListResult(
    val posts: List<PostWithUserInfo>,
    val size: Int,
    val nextPageToken: String?,
)
