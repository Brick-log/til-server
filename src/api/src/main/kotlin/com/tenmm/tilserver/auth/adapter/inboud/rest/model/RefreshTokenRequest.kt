package com.tenmm.tilserver.auth.adapter.inboud.rest.model

data class RefreshTokenRequest(
    val accessToken: String,
    val refreshToken: String,
)
