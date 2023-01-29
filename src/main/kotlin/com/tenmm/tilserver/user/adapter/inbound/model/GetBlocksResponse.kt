package com.tenmm.tilserver.user.adapter.inbound.model

data class GetBlocksResponse(
    val blocks: List<Block>,
)

data class Block(
    val postId: String,
    val postName: String,
    val date: Long,
)
