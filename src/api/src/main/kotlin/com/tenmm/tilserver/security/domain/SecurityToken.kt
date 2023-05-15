package com.tenmm.tilserver.security.domain

import com.tenmm.tilserver.common.domain.Identifier

data class SecurityToken(
    val userIdentifier: Identifier,
    val accessToken: String,
    val refreshToken: String,
)
