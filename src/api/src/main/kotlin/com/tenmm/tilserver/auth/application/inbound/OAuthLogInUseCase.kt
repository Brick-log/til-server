package com.tenmm.tilserver.auth.application.inbound

import com.tenmm.tilserver.auth.application.inbound.model.LogInResult
import com.tenmm.tilserver.auth.domain.OAuthType

interface OAuthLogInUseCase {
    fun logIn(authorizeCode: String, type: OAuthType): LogInResult
}
