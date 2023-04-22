package com.tenmm.tilserver.outbound.persistence.entity

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash

@RedisHash("draft")
data class RefreshTokenEntity(
    @Id
    val userIdentifier: String,
    val refreshToken: String,
)
