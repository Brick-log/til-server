package com.tenmm.tilserver.outbound.persistence.entity

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash

@RedisHash("auth-token")
data class RefreshTokenEntity(
    @Id
    val key: String,
    val refreshToken: String,
)
