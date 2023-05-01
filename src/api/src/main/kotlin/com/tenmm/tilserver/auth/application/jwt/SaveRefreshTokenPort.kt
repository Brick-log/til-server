package com.tenmm.tilserver.auth.application.jwt

interface SaveRefreshTokenPort {
    fun save(userIdentifier: String, refreshToken: String)
}
