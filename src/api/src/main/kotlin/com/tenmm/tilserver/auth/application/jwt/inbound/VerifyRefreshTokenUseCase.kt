package com.tenmm.tilserver.auth.application.jwt.inbound

interface VerifyRefreshTokenUseCase {
    fun verify(accessToken: String, refreshToken: String): Boolean
}
