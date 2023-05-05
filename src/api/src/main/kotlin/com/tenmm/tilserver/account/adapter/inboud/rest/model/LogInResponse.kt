package com.tenmm.tilserver.account.adapter.inboud.rest.model

import com.tenmm.tilserver.account.application.model.LogInResult

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
