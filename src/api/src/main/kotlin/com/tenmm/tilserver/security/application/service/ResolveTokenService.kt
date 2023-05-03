package com.tenmm.tilserver.security.application.service

import com.tenmm.tilserver.common.security.JwtConfigProperties
import com.tenmm.tilserver.security.application.inbound.ResolveTokenUseCase
import com.tenmm.tilserver.security.domain.SecurityTokenType
import com.tenmm.tilserver.common.utils.CryptoHandler
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import javax.crypto.SecretKey
import org.springframework.stereotype.Component

@Component
class ResolveTokenService(
    private val cryptoHandler: CryptoHandler,
    private val jwtConfigProperties: JwtConfigProperties
) : ResolveTokenUseCase {
    override fun resolveToken(token: String, securityTokenType: SecurityTokenType): String {
        val properties =
            if (securityTokenType == SecurityTokenType.ACCESS) jwtConfigProperties.access else jwtConfigProperties.refresh
        val key: SecretKey = Keys.hmacShaKeyFor(properties.secret.toByteArray())
        val jwt = cryptoHandler.decrypt(token, String::class)

        val claims = Jwts.parser()
            .setSigningKey(key)
            .parseClaimsJws(jwt)
            .body

        return claims["id"].toString()
    }
}
