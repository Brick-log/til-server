package com.tenmm.tilserver.security.adapter.inbound.rest.model

data class RefreshTokenRequest(
    val accessToken: String,
    val refreshToken: String,
)
