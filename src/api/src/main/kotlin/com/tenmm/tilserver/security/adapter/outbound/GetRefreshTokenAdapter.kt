package com.tenmm.tilserver.security.adapter.outbound

import com.tenmm.tilserver.security.application.outbound.GetRefreshTokenPort
import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.outbound.persistence.repository.RefreshTokenRepository
import org.springframework.stereotype.Component

@Component
class GetRefreshTokenAdapter(
    private val refreshTokenRepository: RefreshTokenRepository
) : GetRefreshTokenPort {
    override fun checkValidAccessToken(userIdentifier: Identifier, accessToken: String, refreshToken: String): Boolean {
        return refreshTokenRepository.findByKey("${userIdentifier.value}:$accessToken") != null
    }
}
