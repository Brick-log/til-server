package com.tenmm.tilserver.auth.adapter.outbound.oauth.provider

import com.tenmm.tilserver.auth.application.outbound.oauth.model.OAuthTokenResult
import com.tenmm.tilserver.auth.application.outbound.oauth.model.OAuthUserInfoResult
import com.tenmm.tilserver.auth.domain.OAuthType

interface OAuthProvider {
    fun getToken(authorizeCode: String): OAuthTokenResult
    fun getUserInfo(
        accessToken: String,
        refreshToken: String,
        idToken: String? = null,
    ): OAuthUserInfoResult

    fun getProvider(): OAuthType
}
