package com.tenmm.tilserver.auth.application.jwt.service

import com.tenmm.tilserver.auth.application.jwt.inbound.VerifyRefreshTokenUsecase
import com.tenmm.tilserver.auth.application.jwt.GetRefreshTokenPort
import org.springframework.stereotype.Service

@Service
class VerifyResfreshTokenService(
    private val getRefreshTokenPort: GetRefreshTokenPort
) : VerifyRefreshTokenUsecase {
    override fun verify(userIdentifier: String, refreshToken: String): Boolean {
        val savedRefreshToken = getRefreshTokenPort.findByUserIdentifier(userIdentifier)
        // val refreshToken = generateTokenPort.generateToken(userid, TokenType.REFRESH_TOKEN)

        return savedRefreshToken == refreshToken
    }
}
