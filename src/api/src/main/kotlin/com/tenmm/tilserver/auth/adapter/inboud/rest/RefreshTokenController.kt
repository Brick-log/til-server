package com.tenmm.tilserver.auth.adapter.inboud.rest

import com.tenmm.tilserver.auth.adapter.inboud.rest.model.RefreshTokenRequest
import com.tenmm.tilserver.auth.adapter.inboud.rest.model.RefreshTokenResponse
import com.tenmm.tilserver.auth.application.jwt.inbound.RefreshTokenUseCase
import com.tenmm.tilserver.auth.domain.UserAuthInfo
import com.tenmm.tilserver.common.security.annotation.RequiredAuthentication
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/auth")
@Tag(name = "Auth")
class RefreshTokenController(
    private val refreshTokenUseCase: RefreshTokenUseCase,
) {
    @RequiredAuthentication
    @PostMapping("/refresh")
    fun refreshToken(
        userAuthInfo: UserAuthInfo,
        @RequestBody refreshTokenRequest: RefreshTokenRequest,
    ): RefreshTokenResponse {
        val result = refreshTokenUseCase.refresh(
            userAuthInfo,
            refreshTokenRequest.accessToken,
            refreshTokenRequest.refreshToken
        )
        return RefreshTokenResponse(result)
    }
}
