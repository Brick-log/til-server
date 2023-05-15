package com.tenmm.tilserver.security.adapter.outbound

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.common.security.JwtConfigProperties
import com.tenmm.tilserver.common.utils.getNowTimestamp
import com.tenmm.tilserver.outbound.persistence.entity.TokenEntity
import com.tenmm.tilserver.outbound.persistence.repository.TokenRepository
import com.tenmm.tilserver.security.application.outbound.DeleteRefreshTokenPort
import com.tenmm.tilserver.security.application.outbound.GetRefreshTokenPort
import com.tenmm.tilserver.security.application.outbound.SaveRefreshTokenPort
import com.tenmm.tilserver.security.domain.SecurityToken
import java.sql.Timestamp
import mu.KotlinLogging
import org.springframework.stereotype.Component

private val logger = KotlinLogging.logger {}
@Component
class RefreshTokenAdapter(
    val tokenRepository: TokenRepository,
    val jwtTokenProperties: JwtConfigProperties
) : GetRefreshTokenPort, SaveRefreshTokenPort, DeleteRefreshTokenPort {

    override suspend fun getAccessToken(
        userIdentifier: Identifier,
        accessToken: String,
        refreshToken: String
    ): SecurityToken? {
        val entity = tokenRepository.findByUserIdentifierAndAccessTokenAndRefreshTokenExpireIsAfter(
            userIdentifier = userIdentifier.value,
            accessToken = accessToken,
            now = getNowTimestamp()
        )

        return entity?.let {
            SecurityToken(
                userIdentifier = Identifier(it.userIdentifier),
                accessToken = it.accessToken,
                refreshToken = it.refreshToken
            )
        }
    }

    override suspend fun save(
        userIdentifier: Identifier,
        accessToken: String,
        refreshToken: String,
    ): Boolean {
        val now = getNowTimestamp().toInstant()
        val expire = now.plusMillis(jwtTokenProperties.refresh.expire)
        val entity = TokenEntity(
            userIdentifier = userIdentifier.value,
            accessToken = accessToken,
            refreshToken = refreshToken,
            refreshTokenExpire = Timestamp.from(expire)
        )
        return try {
            tokenRepository.save(entity)
            true
        } catch (e: Exception) {
            logger.error(e) { "Token Save Fail - userIdentifier:${userIdentifier.value} accessToken:$accessToken" }
            false
        }
    }

    override suspend fun delete(userIdentifier: Identifier, accessToken: String) {
        val entity = tokenRepository.findByUserIdentifierAndAccessToken(
            userIdentifier = userIdentifier.value,
            accessToken = accessToken
        )

        entity?.let { tokenRepository.delete(entity) }
    }

    override suspend fun deleteExpiredTokens() {
        tokenRepository.deleteAllByRefreshTokenExpireBefore(getNowTimestamp())
    }
}
