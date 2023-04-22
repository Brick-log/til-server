package com.tenmm.tilserver.auth.application.outbound.oauth

import com.tenmm.tilserver.auth.application.outbound.oauth.model.OAuthTokenResult
import com.tenmm.tilserver.auth.domain.OAuthType

interface GetOAuthTokenPort {
    fun getOAuthTokens(authorizeCode: String, type: OAuthType): OAuthTokenResult
}
