package com.tenmm.tilserver.security.application.inbound

import com.tenmm.tilserver.security.domain.SecurityTokenType

interface ResolveTokenUseCase {

    /**
     * 지금은 UserIdentifier만 빼지만, 추후에 객체로 파싱 가능
     */
    fun resolveToken(token: String, securityTokenType: SecurityTokenType): String
}
