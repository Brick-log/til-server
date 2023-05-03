package com.tenmm.tilserver.security.application.outbound

import com.tenmm.tilserver.common.domain.Identifier

interface SaveRefreshTokenPort {
    suspend fun save(userIdentifier: Identifier, accessToken: String, refreshToken: String)
}
