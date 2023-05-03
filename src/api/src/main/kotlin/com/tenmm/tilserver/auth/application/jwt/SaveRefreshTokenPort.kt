package com.tenmm.tilserver.auth.application.jwt

import com.tenmm.tilserver.common.domain.Identifier

interface SaveRefreshTokenPort {
    fun save(userIdentifier: Identifier, accessToken: String, refreshToken: String)
}
