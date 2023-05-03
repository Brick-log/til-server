package com.tenmm.tilserver.security.domain

data class SecurityToken(
    val accessToken: String,
    val refreshToken: String,
)
