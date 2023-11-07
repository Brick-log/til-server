package com.tenmm.tilserver.retrospect.domain

import com.tenmm.tilserver.common.domain.Identifier

data class RetrospectQna(
    val retrospectIdentifier: Identifier,
    val name: String,
    val answer: String
)
