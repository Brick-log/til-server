package com.tenmm.tilserver.security.application.outbound

import com.tenmm.tilserver.common.domain.Identifier

interface SaveRefreshTokenPort {
    fun save(userIdentifier: Identifier, accessToken: String, refreshToken: String)
}
