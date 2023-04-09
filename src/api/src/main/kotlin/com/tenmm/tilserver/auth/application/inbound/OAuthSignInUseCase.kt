package com.tenmm.tilserver.auth.application.inbound

import com.tenmm.tilserver.auth.application.inbound.model.SignInResult
import com.tenmm.tilserver.auth.domain.OAuthType

interface OAuthSignInUseCase {
    fun signIn(authorizeCode: String, type: OAuthType): SignInResult?
}
