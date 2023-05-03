package com.tenmm.tilserver.account.application.outbound

import com.tenmm.tilserver.account.domain.OAuthType

interface GetOAuthTokenPort {
    fun getOAuthTokens(authorizeCode: String, type: OAuthType): OAuthTokenResult
}
