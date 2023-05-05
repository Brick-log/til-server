package com.tenmm.tilserver.account.application.model

data class LogInResult(
    val accessToken: String,
    val refreshToken: String
)
