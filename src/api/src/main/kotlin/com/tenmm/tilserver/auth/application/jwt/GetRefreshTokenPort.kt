package com.tenmm.tilserver.auth.application.jwt

import com.tenmm.tilserver.common.domain.Identifier

interface GetRefreshTokenPort {
    fun checkValidAccessToken(userIdentifier: Identifier, accessToken: String, refreshToken: String): Boolean
}
