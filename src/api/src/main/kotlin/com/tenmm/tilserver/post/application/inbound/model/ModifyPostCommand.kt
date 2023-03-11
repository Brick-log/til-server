package com.tenmm.tilserver.post.application.inbound.model

import com.tenmm.tilserver.common.domain.Identifier

data class ModifyPostCommand(
    val identifier: Identifier,
    val title: String,
    val summary: String,
    val createdAt: Long,
)
