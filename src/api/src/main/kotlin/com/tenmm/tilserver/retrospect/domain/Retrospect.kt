package com.tenmm.tilserver.retrospect.domain

import com.tenmm.tilserver.common.domain.Identifier
import java.sql.Timestamp

data class Retrospect (
    val retrospectIdentifier: Identifier,
    val userIdentifier: Identifier,
    val isSecret: Boolean,
    val createdAt: Timestamp
)
