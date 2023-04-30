package com.tenmm.tilserver.auth.adapter.outbound.jwt

import com.tenmm.tilserver.auth.application.jwt.GetRefreshTokenPort
import com.tenmm.tilserver.outbound.persistence.repository.RefreshTokenRepository
import org.springframework.stereotype.Component

@Component
class GetRefreshTokenAdapter(
    private val refreshTokenRepository: RefreshTokenRepository
) : GetRefreshTokenPort {
    override fun findByUserIdentifier(userIdentifier: String): String? {
        return refreshTokenRepository.findByUserIdentifier(userIdentifier)?.refreshToken
    }
}
