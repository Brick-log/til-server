package com.tenmm.tilserver.post.adapter.inbound.model

data class ModifyPostRequest(
    val title: String,
    val summary: String,
    val url: String,
    val categoryId: String,
)
