package com.tenmm.tilserver.draft.domain

import com.tenmm.tilserver.common.domain.Identifier
import java.sql.Timestamp

data class Draft(
    val userIdentifier: Identifier,
    val data: String,
    val updatedAt: Timestamp,
) {
    companion object {
        const val DRAFT_MAX_LENGTH = 2000
    }

    init {
        if (data.length > DRAFT_MAX_LENGTH) {
            throw IllegalArgumentException("Draft size must lesser then $DRAFT_MAX_LENGTH")
        }
    }
}
