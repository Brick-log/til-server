package com.tenmm.tilserver.auth.adapter.outbound.jwt

import com.tenmm.tilserver.auth.application.jwt.SaveRefreshTokenPort
import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.outbound.persistence.entity.RefreshTokenEntity
import com.tenmm.tilserver.outbound.persistence.repository.RefreshTokenRepository
import org.springframework.stereotype.Component

@Component
class SaveRefreshTokenAdapter(
    private val refreshTokenRepository: RefreshTokenRepository
) : SaveRefreshTokenPort {
    override fun save(userIdentifier: Identifier, accessToken: String, refreshToken: String) {
        RefreshTokenEntity(
            key = "${userIdentifier.value}:$accessToken",
            refreshToken = refreshToken
        ).let {
            refreshTokenRepository.save(it)
        }
    }
}
