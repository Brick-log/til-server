package com.tenmm.tilserver.outbound.persistence.entity

import org.springframework.data.redis.core.RedisHash
import jakarta.persistence.Id
import java.time.LocalDateTime

@RedisHash("member")
data class DraftSyncEntity(
    @Id
    val id: Long = 0,

    val userIdentifier: String,

    val data: String,

    val updatedAt: LocalDateTime,
)
