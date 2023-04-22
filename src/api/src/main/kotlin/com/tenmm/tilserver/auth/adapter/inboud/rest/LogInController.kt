package com.tenmm.tilserver.auth.adapter.inboud.rest

import com.tenmm.tilserver.auth.adapter.inboud.rest.model.LogInRequest
import com.tenmm.tilserver.auth.adapter.inboud.rest.model.LogInResponse
import com.tenmm.tilserver.auth.application.inbound.OAuthLogInUseCase
import com.tenmm.tilserver.common.adapter.inbound.rest.model.ErrorResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/auth")
@Tag(name = "Auth")
class LogInController(
    private val oAuthLogInUseCase: OAuthLogInUseCase
) {

    @PostMapping("/login")
    @Operation(
        summary = "로그인",
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "로그인 성공"
            ),
            ApiResponse(
                responseCode = "500",
                description = "서버에러",
                content = [Content(schema = Schema(implementation = ErrorResponse::class))]
            )
        ]
    )
    fun login(
        @RequestBody req: LogInRequest
    ): LogInResponse {

        val result = oAuthLogInUseCase.logIn(
            authorizeCode = req.token,
            type = req.type
        )
        return LogInResponse.fromResult(result)
    }
}
