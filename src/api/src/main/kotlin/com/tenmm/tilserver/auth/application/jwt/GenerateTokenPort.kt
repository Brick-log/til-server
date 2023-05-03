package com.tenmm.tilserver.auth.application.jwt

import com.tenmm.tilserver.auth.domain.TokenType
import com.tenmm.tilserver.common.domain.Identifier

interface GenerateTokenPort {
    fun generateToken(userIdentifier: Identifier, tokenType: TokenType): String
}
