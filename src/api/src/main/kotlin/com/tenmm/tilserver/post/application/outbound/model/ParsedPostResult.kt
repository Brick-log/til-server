package com.tenmm.tilserver.post.application.outbound.model

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.common.domain.Url
import java.sql.Timestamp
import java.time.LocalDateTime

data class ParsedPostResult(
    val identifier: Identifier,
    val userIdentifier: Identifier,
    val url: Url,
    val title: String,
    val description: String?,
    val createdAt: Timestamp?,
)
