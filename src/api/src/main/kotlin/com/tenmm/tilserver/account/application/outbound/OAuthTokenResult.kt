package com.tenmm.tilserver.account.application.outbound

data class OAuthTokenResult(
    val accessToken: String,
    val refreshToken: String,
    val idToken: String?
)
