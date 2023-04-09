package com.tenmm.tilserver.auth.adapter.outbound.oauth.client

import com.tenmm.tilserver.auth.application.outbound.model.OAuthTokenResult
import com.tenmm.tilserver.auth.application.outbound.model.OAuthUserInfoResult
import com.tenmm.tilserver.auth.domain.OAuthType

interface OAuthClient {
    fun getToken(authorizeCode: String): OAuthTokenResult
    fun getUserInfo(
        accessToken: String,
        refreshToken: String,
        idToken: String? = null,
    ): OAuthUserInfoResult

    fun getProvider(): OAuthType
    fun getAuthorizationCodeRedirectURI(): String
}
