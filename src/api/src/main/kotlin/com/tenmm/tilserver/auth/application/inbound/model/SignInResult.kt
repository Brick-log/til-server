package com.tenmm.tilserver.auth.application.inbound.model

data class SignInResult(
    val accessToken: String,
    val refreshToken: String
)
