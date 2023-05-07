package com.tenmm.tilserver.security.application.service

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.common.security.JwtConfigProperties
import com.tenmm.tilserver.common.utils.CryptoHandler
import com.tenmm.tilserver.security.application.inbound.ResolveTokenUseCase
import com.tenmm.tilserver.security.domain.SecurityTokenType
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import javax.crypto.SecretKey
import org.springframework.stereotype.Component

@Component
class ResolveTokenService(
    private val cryptoHandler: CryptoHandler,
    private val jwtConfigProperties: JwtConfigProperties
) : ResolveTokenUseCase {
    override fun resolveToken(token: String, securityTokenType: SecurityTokenType): Identifier {
        val properties = when (securityTokenType) {
            SecurityTokenType.ACCESS -> {
                jwtConfigProperties.access
            }

            SecurityTokenType.REFRESH -> {
                jwtConfigProperties.refresh
            }
        }
        val key: SecretKey = Keys.hmacShaKeyFor(properties.secret.toByteArray())
        val jwt = cryptoHandler.decrypt(token, String::class)

        val claims = Jwts.parser()
            .setSigningKey(key)
            .parseClaimsJws(jwt)
            .body

        return Identifier(claims["id"].toString())
    }
}
