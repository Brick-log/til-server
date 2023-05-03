package com.tenmm.tilserver.security.application.inbound

import com.tenmm.tilserver.security.domain.SecurityToken
import com.tenmm.tilserver.security.domain.UserAuthInfo

interface RefreshTokenUseCase {
    suspend fun refresh(userAuthInfo: UserAuthInfo, securityToken: SecurityToken): SecurityToken
}
