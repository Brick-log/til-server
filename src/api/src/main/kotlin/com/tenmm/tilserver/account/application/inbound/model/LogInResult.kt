package com.tenmm.tilserver.account.application.inbound.model

data class LogInResult(
    val accessToken: String,
    val refreshToken: String
)
