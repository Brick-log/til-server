package com.tenmm.tilserver.outbound.persistence.entity

import java.sql.Timestamp
import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash

@RedisHash("post.temp")
data class ParsedPostEntity(
    @Id
    val identifier: String,
    val userIdentifier: String,
    val url: String,
    val title: String,
    val description: String?,
    val createdAt: Timestamp?,
)
