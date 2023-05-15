package com.tenmm.tilserver.security.application.outbound

import com.tenmm.tilserver.common.domain.Identifier

interface DeleteRefreshTokenPort {
    suspend fun delete(userIdentifier: Identifier, accessToken: String)
    suspend fun deleteExpiredTokens()
}
