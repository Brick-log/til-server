package com.tenmm.tilserver.draft.adapter.outbound

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.draft.domain.Draft
import com.tenmm.tilserver.outbound.persistence.entity.DraftEntity
import com.tenmm.tilserver.outbound.persistence.entity.DraftSyncEntity

fun DraftEntity.toDomain(): Draft {
    return Draft(
        userIdentifier = Identifier(this.userIdentifier),
        data = this.data,
        updatedAt = this.updatedAt,
    )
}

fun DraftSyncEntity.toDomain(): Draft {
    return Draft(
        userIdentifier = Identifier(this.userIdentifier),
        data = this.data,
        updatedAt = this.updatedAt,
    )
}
