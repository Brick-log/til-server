package com.tenmm.tilserver.post.adapter.inbound.rest.model

data class ModifyPostRequest(
    val title: String,
    val summary: String,
    val createdAt: Long,
)
