package com.tenmm.tilserver.blog.adapter.inbound.model

data class GetBlogResponse(val blogs: List<BlogResult>)

data class BlogResult(
    val identifier: String,
    val url: String,
)
