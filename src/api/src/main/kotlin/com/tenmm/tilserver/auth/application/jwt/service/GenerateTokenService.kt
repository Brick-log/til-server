package com.tenmm.tilserver.auth.application.jwt.service

import com.tenmm.tilserver.auth.application.jwt.GenerateTokenPort
import com.tenmm.tilserver.auth.application.jwt.SaveRefreshTokenPort
import com.tenmm.tilserver.auth.application.jwt.inbound.GenerateTokenUseCase
import com.tenmm.tilserver.auth.domain.TokenType
import com.tenmm.tilserver.auth.domain.UserAuthToken
import com.tenmm.tilserver.common.domain.Identifier
import org.springframework.stereotype.Service

@Service
class GenerateTokenService(
    private val generateTokenPort: GenerateTokenPort,
    private val saveRefreshTokenPort: SaveRefreshTokenPort,
) : GenerateTokenUseCase {
    override fun generate(userIdentifier: Identifier): UserAuthToken {
        val accessToken = generateTokenPort.generateToken(userIdentifier, TokenType.ACCESS)
        val refreshToken = generateTokenPort.generateToken(userIdentifier, TokenType.REFRESH)
        saveRefreshTokenPort.save(userIdentifier, accessToken, refreshToken)
        return UserAuthToken(accessToken, refreshToken)
    }
}
