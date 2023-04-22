package com.tenmm.tilserver.auth.application.outbound.oauth.model

data class OAuthTokenResult(
    val accessToken: String,
    val refreshToken: String,
    val idToken: String?
)
