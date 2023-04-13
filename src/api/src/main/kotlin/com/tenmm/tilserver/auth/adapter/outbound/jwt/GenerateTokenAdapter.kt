package com.tenmm.tilserver.auth.adapter.outbound.jwt

import com.tenmm.tilserver.auth.adapter.outbound.jwt.config.JwtConfigProperties
import com.tenmm.tilserver.auth.application.jwt.GenerateTokenPort
import com.tenmm.tilserver.auth.domain.TokenType
import com.tenmm.tilserver.common.utils.CryptoHandler
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import java.time.ZoneId
import java.time.ZonedDateTime
import java.util.Date
import javax.crypto.SecretKey
import org.springframework.stereotype.Component

@Component
class GenerateTokenAdapter(
    private val cryptoHandler: CryptoHandler,
    private val jwtConfigProperties: JwtConfigProperties
) : GenerateTokenPort {
    override fun generateToken(userIdentifier: String, tokenType: TokenType): String {
        val properties =
            if (tokenType == TokenType.ACCESS) jwtConfigProperties.access else jwtConfigProperties.refresh

        val key: SecretKey = Keys.hmacShaKeyFor(properties.secret.toByteArray())
        val now = ZonedDateTime.now(ZoneId.of("Asia/Seoul")).toInstant().toEpochMilli()
        val expiredAt = now + properties.expire

        val jwt = Jwts.builder()
            .claim("id", userIdentifier)
            .setIssuedAt(Date(now))
            .setExpiration(Date(expiredAt))
            .signWith(key, SignatureAlgorithm.HS256)
            .compact()

        return cryptoHandler.encrypt(jwt)
    }
}
