package com.tenmm.tilserver.user.domain

import com.tenmm.tilserver.common.domain.Identifier

data class User(
    val identifier: Identifier,
    val name: String,
    val introduction: String,
    val thumbnailUrl: String,
    val categoryIdentifier: Identifier,
)
