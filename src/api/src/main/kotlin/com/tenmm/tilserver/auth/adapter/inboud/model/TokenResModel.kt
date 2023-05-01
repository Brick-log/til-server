package com.tenmm.tilserver.auth.adapter.inboud.model

data class TokenResModel(
    val accessToken: String,
    val refreshToken: String
)
