package com.tenmm.tilserver.outbound.persistence.entity

import org.springframework.data.redis.core.RedisHash
import org.springframework.data.annotation.Id
import java.time.LocalDateTime
// import Serializable
import java.io.Serializable

@RedisHash("member")
data class DraftSyncEntity:Serializable (
    @Id
    val userIdentifier: String,

    val data: String,

    val updatedAt: LocalDateTime,
)
