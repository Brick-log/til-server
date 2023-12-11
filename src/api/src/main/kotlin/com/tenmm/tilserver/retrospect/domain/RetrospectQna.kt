package com.tenmm.tilserver.retrospect.domain

import com.tenmm.tilserver.common.domain.Identifier

data class RetrospectQna(
    val retrospectIdentifier: Identifier,
    val questionName: String,
    val answer: String
)
