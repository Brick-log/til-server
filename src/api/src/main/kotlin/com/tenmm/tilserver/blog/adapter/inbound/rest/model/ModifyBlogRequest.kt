package com.tenmm.tilserver.blog.adapter.inbound.rest.model

data class ModifyBlogRequest(
    val blogs: List<ModifyBlogInfoRequest>,
)

data class ModifyBlogInfoRequest(
    val url: String,
)
