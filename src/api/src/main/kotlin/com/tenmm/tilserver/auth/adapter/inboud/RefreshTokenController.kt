package com.tenmm.tilserver.auth.adapter.inboud

import com.tenmm.tilserver.auth.application.jwt.service.GenerateTokenService
import com.tenmm.tilserver.auth.application.jwt.inbound.VerifyRefreshTokenUseCase
import com.tenmm.tilserver.auth.adapter.inboud.model.TokenResModel
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import com.tenmm.tilserver.auth.adapter.inboud.model.RefreshTokenReqModel
import com.tenmm.tilserver.auth.adapter.outbound.jwt.config.JwtConfigProperties
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import javax.crypto.SecretKey

@RestController("/api/auth")
class RefreshTokenController(
    private val generateTokenService: GenerateTokenService,
    private val verifyRefreshTokenUsecase: VerifyRefreshTokenUseCase,
    private val jwtConfigProperties: JwtConfigProperties
) {
    @PostMapping("/refresh")
    fun refreshToken(@RequestHeader("Authorization") accessToken: String, @RequestBody refreshTokenReqModel: RefreshTokenReqModel): TokenResModel {
        val refreshToken = refreshTokenReqModel.refreshToken
        val properties = jwtConfigProperties.access
        val key: SecretKey = Keys.hmacShaKeyFor(properties.secret.toByteArray())
        val userId = Jwts.parser().setSigningKey(key).parseClaimsJws(refreshToken).body["id"] as String

        if (verifyRefreshTokenUsecase.verify(accessToken, refreshToken)) {
            return generateTokenService.generate(userId)
        }

        throw RuntimeException("Invalid refresh token")
    }
}
