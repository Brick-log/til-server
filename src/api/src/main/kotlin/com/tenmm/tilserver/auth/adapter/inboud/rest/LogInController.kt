package com.tenmm.tilserver.auth.adapter.inboud.rest

import com.tenmm.tilserver.auth.adapter.inboud.rest.model.LogInRequest
import com.tenmm.tilserver.auth.adapter.inboud.rest.model.LogInResponse
import com.tenmm.tilserver.auth.application.inbound.OAuthLogInUseCase
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/auth")
class LogInController(
    private val oAuthLogInUseCase: OAuthLogInUseCase
) {

    @PostMapping("/login")
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
