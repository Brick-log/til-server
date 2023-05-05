package com.tenmm.tilserver.security.adapter.outbound

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.security.application.outbound.DeleteSecurityTokenPort
import com.tenmm.tilserver.security.application.outbound.GetRefreshTokenPort
import com.tenmm.tilserver.security.application.outbound.SaveRefreshTokenPort
import org.springframework.data.redis.core.ReactiveRedisTemplate
import org.springframework.data.redis.core.deleteAndAwait
import org.springframework.data.redis.core.getAndAwait
import org.springframework.data.redis.core.setAndAwait
import org.springframework.stereotype.Component

@Component
class SecurityTokenAdapter(
    securityTokenRedisTemplate: ReactiveRedisTemplate<String, String>,
) : GetRefreshTokenPort, SaveRefreshTokenPort, DeleteSecurityTokenPort {

    private val ops = securityTokenRedisTemplate.opsForValue()

    private fun generateKey(userIdentifier: Identifier, accessToken: String): String {
        return "security:${userIdentifier.value}:$accessToken"
    }

    override suspend fun checkValidAccessToken(
        userIdentifier: Identifier,
        accessToken: String,
        refreshToken: String,
    ): Boolean {
        val key = generateKey(userIdentifier, accessToken)
        return ops.getAndAwait(key) != null
    }

    override suspend fun save(
        userIdentifier: Identifier,
        accessToken: String,
        refreshToken: String,
    ) {
        val key = generateKey(userIdentifier, accessToken)
        ops.setAndAwait(key, refreshToken)
    }

    override suspend fun delete(userIdentifier: Identifier, accessToken: String) {
        val key = generateKey(userIdentifier, accessToken)
        ops.deleteAndAwait(key)
    }
}
