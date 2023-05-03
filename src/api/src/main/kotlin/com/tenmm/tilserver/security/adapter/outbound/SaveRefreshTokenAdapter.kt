package com.tenmm.tilserver.security.adapter.outbound

import com.tenmm.tilserver.security.application.outbound.SaveRefreshTokenPort
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
