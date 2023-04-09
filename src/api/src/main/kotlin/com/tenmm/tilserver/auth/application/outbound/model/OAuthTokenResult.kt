package com.tenmm.tilserver.auth.application.outbound.model

data class OAuthTokenResult(
    val accessToken: String,
    val refreshToken: String,
    val idToken: String?
)
