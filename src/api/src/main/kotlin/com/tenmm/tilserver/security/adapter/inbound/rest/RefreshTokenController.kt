package com.tenmm.tilserver.security.adapter.inbound.rest

import com.tenmm.tilserver.security.adapter.inbound.rest.model.RefreshTokenRequest
import com.tenmm.tilserver.security.adapter.inbound.rest.model.RefreshTokenResponse
import com.tenmm.tilserver.security.application.inbound.RefreshTokenUseCase
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
    @PostMapping("/refresh")
    suspend fun refreshToken(
        @RequestBody refreshTokenRequest: RefreshTokenRequest,
    ): RefreshTokenResponse {
        val result = refreshTokenUseCase.refresh(
            accessToken = refreshTokenRequest.accessToken,
            refreshToken = refreshTokenRequest.refreshToken

        )
        return RefreshTokenResponse(result.accessToken)
    }
}
