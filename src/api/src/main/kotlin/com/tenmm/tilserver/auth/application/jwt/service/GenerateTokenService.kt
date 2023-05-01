package com.tenmm.tilserver.auth.application.jwt.service

import com.tenmm.tilserver.auth.adapter.inboud.model.TokenResModel
import com.tenmm.tilserver.auth.application.jwt.GenerateTokenPort
import com.tenmm.tilserver.auth.application.jwt.SaveRefreshTokenPort
import com.tenmm.tilserver.auth.application.jwt.inbound.GenerateTokenUsecase
import com.tenmm.tilserver.auth.domain.TokenType
import org.springframework.stereotype.Service

@Service
class GenerateTokenService(
    private val generateTokenPort: GenerateTokenPort,
    private val saveRefreshTokenPort: SaveRefreshTokenPort
) : GenerateTokenUsecase {
    override fun generate(userIdentifier: String): TokenResModel {
        val accessToken = generateTokenPort.generateToken(userIdentifier, TokenType.ACCESS)
        val refreshToken = generateTokenPort.generateToken(userIdentifier, TokenType.REFRESH)
        saveRefreshTokenPort.save(userIdentifier, refreshToken)
        return TokenResModel(accessToken, refreshToken)
    }
}
