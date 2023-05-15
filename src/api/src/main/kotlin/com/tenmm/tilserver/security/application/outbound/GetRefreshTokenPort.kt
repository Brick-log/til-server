package com.tenmm.tilserver.security.application.outbound

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.security.domain.SecurityToken

interface GetRefreshTokenPort {
    suspend fun getAccessToken(userIdentifier: Identifier, accessToken: String, refreshToken: String): SecurityToken?
}
