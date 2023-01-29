package com.tenmm.tilserver.post.adapter.inbound.model

data class GetPostListResponse(
    val postList: List<Post>,
    val nextPageToken: String,
    val prevPageToken: String,
)

data class Post(
    val id: String,
    val title: String,
    val summary: String,
    val author: String,
    val url: String,
    val createdAt: Long,
)
