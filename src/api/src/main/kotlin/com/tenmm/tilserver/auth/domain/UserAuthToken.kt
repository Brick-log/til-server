package com.tenmm.tilserver.auth.domain

data class UserAuthToken(
    val accessToken: String,
    val refreshToken: String,
)
