package com.tenmm.tilserver.auth.application.jwt.inbound

interface VerifyRefreshTokenUsecase {
    fun verify(accessToken: String, refreshToken: String): Boolean
}
