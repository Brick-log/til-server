package com.tenmm.tilserver.auth.application.jwt

import com.tenmm.tilserver.auth.domain.TokenType

interface ResolveTokenPort {

    /**
     * 지금은 UserIdentifier만 빼지만, 추후에 객체로 파싱 가능
     */
    fun resolveToken(token: String, tokenType: TokenType): String
}
