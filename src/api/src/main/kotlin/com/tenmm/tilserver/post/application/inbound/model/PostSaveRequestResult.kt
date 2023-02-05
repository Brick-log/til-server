package com.tenmm.tilserver.post.application.inbound.model

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.common.domain.Url
import java.sql.Timestamp

data class PostSaveRequestResult(
    val identifier: Identifier,
    val title: String,
    val description: String,
    val url: Url,
    val createdAt: Timestamp,
)
