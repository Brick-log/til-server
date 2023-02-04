package com.tenmm.tilserver.post.domain

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.common.domain.Url
import java.math.BigInteger
import java.sql.Timestamp

data class Post(
    val userIdentifier: Identifier,
    val title: String,
    val description: String,
    val url: Url,
    val createdAt: Timestamp,
    val categoryIdentifier: Identifier,
    val hitCount: BigInteger,
)
