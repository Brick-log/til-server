package com.tenmm.tilserver.outbound.persistence.entity

import org.springframework.data.redis.core.RedisHash
import org.springframework.data.annotation.Id

@RedisHash("crawling")
data class CrawlingEntity(
    @Id
    val identifier: String,

    val title: String,

    val createdAt: String,

    val description: String,
)
