package com.tenmm.tilserver.security.adapter.outbound

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.security.application.outbound.GetRefreshTokenPort
import com.tenmm.tilserver.security.application.outbound.SaveRefreshTokenPort
import org.springframework.data.redis.core.ReactiveRedisTemplate
import org.springframework.data.redis.core.getAndAwait
import org.springframework.data.redis.core.setAndAwait
import org.springframework.stereotype.Component

@Component
class RefreshTokenAdapter(
    securityTokenRedisTemplate: ReactiveRedisTemplate<String, String>,
) : GetRefreshTokenPort, SaveRefreshTokenPort {

    private val ops = securityTokenRedisTemplate.opsForValue()

    override suspend fun checkValidAccessToken(
        userIdentifier: Identifier,
        accessToken: String,
        refreshToken: String,
    ): Boolean {
        val key = "security:${userIdentifier.value}:$accessToken"
        return ops.getAndAwait(key) != null
    }

    override suspend fun save(userIdentifier: Identifier, accessToken: String, refreshToken: String) {
        val key = "security:${userIdentifier.value}:$accessToken"
        ops.setAndAwait(key, refreshToken)
    }
}
