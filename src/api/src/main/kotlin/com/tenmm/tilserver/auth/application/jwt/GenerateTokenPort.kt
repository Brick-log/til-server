package com.tenmm.tilserver.auth.application.jwt

import com.tenmm.tilserver.auth.domain.TokenType

interface GenerateTokenPort {
    fun generateToken(userIdentifier: String, tokenType: TokenType): String

}