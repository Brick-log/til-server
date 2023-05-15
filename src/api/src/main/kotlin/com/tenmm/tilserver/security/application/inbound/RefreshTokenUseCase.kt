package com.tenmm.tilserver.security.application.inbound

import com.tenmm.tilserver.security.domain.SecurityToken

interface RefreshTokenUseCase {
    suspend fun refresh(securityToken: SecurityToken): SecurityToken
}
