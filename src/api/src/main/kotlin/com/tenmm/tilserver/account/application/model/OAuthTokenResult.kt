package com.tenmm.tilserver.account.application.model

data class OAuthTokenResult(
    val accessToken: String,
    val refreshToken: String,
    val idToken: String?
)
