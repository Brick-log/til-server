package com.tenmm.tilserver.auth.application.outbound

import com.tenmm.tilserver.auth.application.outbound.model.OAuthTokenResult
import com.tenmm.tilserver.auth.domain.OAuthType

interface GetOAuthTokenPort {
    fun getOAuthTokens(authorizeCode: String, type: OAuthType): OAuthTokenResult
}
