package com.tenmm.tilserver.security.application.outbound

import com.tenmm.tilserver.common.domain.Identifier

interface GetRefreshTokenPort {
    fun checkValidAccessToken(userIdentifier: Identifier, accessToken: String, refreshToken: String): Boolean
}
