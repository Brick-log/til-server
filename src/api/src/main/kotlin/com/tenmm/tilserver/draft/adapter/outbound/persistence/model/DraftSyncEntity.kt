package com.tenmm.tilserver.draft.adapter.outbound.persistence.model

import org.springframework.data.redis.core.RedisHash
import com.tenmm.tilserver.common.domain.Identifier
import javax.persistence.Id

@RedisHash("draft")
data class DraftSyncEntity(
    @Id
    val userIdentifier: Identifier,
    val data: String? = null,
)
