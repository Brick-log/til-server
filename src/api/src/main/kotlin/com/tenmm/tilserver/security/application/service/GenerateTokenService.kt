package com.tenmm.tilserver.security.application.service

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.common.security.JwtConfigProperties
import com.tenmm.tilserver.common.utils.CryptoHandler
import com.tenmm.tilserver.common.utils.getNowTimestamp
import com.tenmm.tilserver.security.application.inbound.GenerateTokenUseCase
import com.tenmm.tilserver.security.application.outbound.SaveRefreshTokenPort
import com.tenmm.tilserver.security.domain.SecurityToken
import com.tenmm.tilserver.security.domain.SecurityTokenType
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import java.util.Date
import javax.crypto.SecretKey
import org.springframework.stereotype.Service

@Service
class GenerateTokenService(
    private val saveRefreshTokenPort: SaveRefreshTokenPort,
    private val cryptoHandler: CryptoHandler,
    private val jwtConfigProperties: JwtConfigProperties,
) : GenerateTokenUseCase {
    override suspend fun generate(userIdentifier: Identifier): SecurityToken {
        val accessToken = generateToken(userIdentifier, SecurityTokenType.ACCESS)
        val refreshToken = generateToken(userIdentifier, SecurityTokenType.REFRESH)
        saveRefreshTokenPort.save(userIdentifier, accessToken, refreshToken)
        return SecurityToken(accessToken, refreshToken)
    }

    private fun generateToken(userIdentifier: Identifier, securityTokenType: SecurityTokenType): String {
        val properties = when (securityTokenType) {
            SecurityTokenType.ACCESS -> {
                jwtConfigProperties.access
            }

            SecurityTokenType.REFRESH -> {
                jwtConfigProperties.refresh
            }
        }
        val key: SecretKey = Keys.hmacShaKeyFor(properties.secret.toByteArray())
        val now = getNowTimestamp().time
        val expiredAt = now + properties.expire

        val jwt = Jwts.builder()
            .claim("id", userIdentifier.value)
            .setIssuedAt(Date(now))
            .setExpiration(Date(expiredAt))
            .signWith(key, SignatureAlgorithm.HS256)
            .compact()

        return cryptoHandler.encrypt(jwt)
    }
}
