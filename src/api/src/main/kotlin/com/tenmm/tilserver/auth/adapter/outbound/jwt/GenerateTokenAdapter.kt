package com.tenmm.tilserver.auth.adapter.outbound.jwt

import com.tenmm.tilserver.auth.adapter.outbound.jwt.config.JwtConfigProperties
import com.tenmm.tilserver.auth.application.jwt.GenerateTokenPort
import com.tenmm.tilserver.auth.domain.TokenType
import org.springframework.stereotype.Component

@Component
class GenerateTokenAdapter(
    val jwtConfigProperties: JwtConfigProperties
) : GenerateTokenPort{
    override fun generateToken(userIdentifier: String, tokenType: TokenType): String {
    }

}