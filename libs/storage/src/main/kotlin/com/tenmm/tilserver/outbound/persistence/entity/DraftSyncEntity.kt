package com.tenmm.tilserver.outbound.persistence.entity

import java.sql.Timestamp
import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash

@RedisHash("draft")
data class DraftSyncEntity(
    @Id
    val userIdentifier: String,

    val data: String,

    val updatedAt: Timestamp,
)
