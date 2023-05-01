package com.tenmm.tilserver.blog.adapter.inbound.rest.model

data class ModifyBlogRequest(
    val infoList: List<ModifyBlogInfoRequest>,
)

data class ModifyBlogInfoRequest(
    val url: String,
)
