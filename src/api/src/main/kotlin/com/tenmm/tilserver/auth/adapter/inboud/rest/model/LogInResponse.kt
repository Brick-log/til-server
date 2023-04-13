package com.tenmm.tilserver.auth.adapter.inboud.rest.model

import com.tenmm.tilserver.auth.application.inbound.model.LogInResult

data class LogInResponse(
    val accessToken: String,
    val refreshToken: String
) {
    companion object {
        fun fromResult(loginResult: LogInResult): LogInResponse {
            return LogInResponse(
                accessToken = loginResult.accessToken,
                refreshToken = loginResult.refreshToken
            )
        }
    }
}
