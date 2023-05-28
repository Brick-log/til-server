package com.tenmm.tilserver.account.application.outbound

import com.tenmm.tilserver.account.application.model.OAuthTokenResult
import com.tenmm.tilserver.account.domain.OAuthType
import com.tenmm.tilserver.common.domain.Url

interface GetOAuthTokenPort {
    fun getOAuthTokens(authorizeCode: String, type: OAuthType, redirectUrl: Url): OAuthTokenResult
}
