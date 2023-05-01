package com.tenmm.tilserver.auth.application.jwt.service

import com.tenmm.tilserver.auth.application.jwt.GetRefreshTokenPort
import com.tenmm.tilserver.auth.application.jwt.inbound.VerifyRefreshTokenUseCase
import org.springframework.stereotype.Service

@Service
class VerifyResfreshTokenService(
    private val getRefreshTokenPort: GetRefreshTokenPort
) : VerifyRefreshTokenUseCase {
    override fun verify(accessToken: String, refreshToken: String): Boolean {
        val savedRefreshToken = getRefreshTokenPort.findByUserIdentifier(accessToken)
        // val refreshToken = generateTokenPort.generateToken(userid, TokenType.REFRESH_TOKEN)

        return savedRefreshToken == refreshToken
    }
}
