package com.tenmm.tilserver.blog.adapter.inbound.model

import com.tenmm.tilserver.blog.application.inbound.model.GetBlogResult

data class GetBlogResponse(val blogs: List<BlogResult>)

data class BlogResult(
    val identifier: String,
    val url: String,
)
