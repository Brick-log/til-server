package com.tenmm.tilserver.user.domain

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.common.domain.Url

data class User(
    val identifier: Identifier,
    val name: String,
    val path: String,
    val introduction: String,
    val thumbnailUrl: Url,
    val categoryIdentifier: Identifier,
)
