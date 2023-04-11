package com.tenmm.tilserver.auth.adapter.outbound.jwt

import com.tenmm.tilserver.auth.adapter.outbound.jwt.config.JwtConfigProperties
import com.tenmm.tilserver.auth.application.jwt.ResolveTokenPort
import com.tenmm.tilserver.auth.domain.TokenType
import com.tenmm.tilserver.common.utils.CryptoHandler
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import javax.crypto.SecretKey
import org.springframework.stereotype.Component

@Component
class ResolveTokenAdapter(
    private val cryptoHandler: CryptoHandler,
    private val jwtConfigProperties: JwtConfigProperties
) : ResolveTokenPort {
    override fun resolveToken(token: String, tokenType: TokenType): String {
        val properties =
            if (tokenType == TokenType.ACCESS) jwtConfigProperties.access else jwtConfigProperties.refresh
        val key: SecretKey = Keys.hmacShaKeyFor(properties.secret.toByteArray())
        val jwt = cryptoHandler.decrypt(token, String::class)

        val claims = Jwts.parser()
            .setSigningKey(key)
            .parseClaimsJws(jwt)
            .body

        return claims["id"].toString()
    }
}
