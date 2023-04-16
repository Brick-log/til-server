package com.tenmm.tilserver.auth.adapter.outbound.jwt

import com.tenmm.tilserver.auth.application.jwt.SaveRefreshTokenPort
import com.tenmm.tilserver.outbound.persistence.repository.RefreshTokenRepository
import com.tenmm.tilserver.outbound.persistence.entity.RefreshTokenEntity
import org.springframework.stereotype.Component

@Component
class SaveRefreshTokenAdapter(
    private val refreshTokenRepository: RefreshTokenRepository
) : SaveRefreshTokenPort {
    override fun save(userIdentifier: String, refreshToken: String) {
        RefreshTokenEntity(
            userIdentifier = userIdentifier,
            refreshToken = refreshToken
        ).let {
            refreshTokenRepository.save(it)
        }
    }
}
