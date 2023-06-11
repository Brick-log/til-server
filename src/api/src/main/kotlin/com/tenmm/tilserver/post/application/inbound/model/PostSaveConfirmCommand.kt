package com.tenmm.tilserver.post.application.inbound.model

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.common.domain.Url
import java.sql.Timestamp

data class PostSaveConfirmCommand(
    val userIdentifier: Identifier,
    val url: Url,
    val title: String,
    val description: String,
    val createdAt: Timestamp,
)
