package com.tenmm.tilserver.post.application.inbound.model

import com.tenmm.tilserver.common.domain.Url
import java.sql.Timestamp

data class PostSaveRequestResult(
    val url: Url,
    val title: String,
    val description: String?,
    val createdAt: Timestamp?,
)
