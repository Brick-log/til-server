package com.tenmm.tilserver.auth.application.jwt.inbound

import com.tenmm.tilserver.auth.domain.UserAuthInfo

interface RefreshTokenUseCase {
    fun refresh(userAuthInfo: UserAuthInfo, accessToken: String, refreshToken: String): String
}
