package com.tenmm.tilserver.auth.application.inbound.model

data class LogInResult(
    val accessToken: String,
    val refreshToken: String
)
