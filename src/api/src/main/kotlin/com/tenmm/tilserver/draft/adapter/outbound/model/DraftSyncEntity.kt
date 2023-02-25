package com.tenmm.tilserver.draft.adapter.outbound.model

import org.springframework.data.redis.core.RedisHash
import com.tenmm.tilserver.common.domain.Identifier
// import javax.persistence.Id
import java.sql.Timestamp

@RedisHash("draft")
data class DraftSyncEntity(
    // @Id
    val Id: Long = 0,
    val UserIdentifier: Identifier,
    val Data: String? = null,
    val UpdatedAt: Timestamp,
)
