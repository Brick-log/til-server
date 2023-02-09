package com.tenmm.tilserver.post.domain

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.common.domain.Url
import java.math.BigInteger
import java.sql.Timestamp

data class Post(
    val identifier: Identifier,
    val userIdentifier: Identifier,
    val categoryIdentifier: Identifier,
    val title: String,
    val description: String,
    val url: Url,
    val createdAt: Timestamp,
    val hitCount: BigInteger,
)
