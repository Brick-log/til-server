package com.tenmm.tilserver.account.adapter.inboud.rest

import com.tenmm.tilserver.account.adapter.inboud.rest.model.LogInRequest
import com.tenmm.tilserver.account.adapter.inboud.rest.model.LogInResponse
import com.tenmm.tilserver.account.adapter.inboud.rest.model.LogOutResponse
import com.tenmm.tilserver.account.application.inbound.LogInUseCase
import com.tenmm.tilserver.account.application.inbound.model.LogInCommand
import com.tenmm.tilserver.common.exception.ErrorResponse
import com.tenmm.tilserver.common.security.annotation.RequiredAuthentication
import com.tenmm.tilserver.security.domain.UserAuthInfo
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
class LogInOutController(
    private val logInUseCase: LogInUseCase,
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
    suspend fun login(
        @RequestBody req: LogInRequest,
    ): LogInResponse {
        val result = logInUseCase.logIn(
            LogInCommand(
                authorizeCode = req.token,
                type = req.type
            )
        )
        return LogInResponse.fromResult(result)
    }

    @RequiredAuthentication
    @PostMapping("/logout")
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
    fun logout(
        userAuthInfo: UserAuthInfo,
    ): LogOutResponse {
        return LogOutResponse(true)
    }
}
