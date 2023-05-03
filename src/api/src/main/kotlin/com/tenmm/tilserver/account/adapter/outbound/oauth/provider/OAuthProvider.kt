package com.tenmm.tilserver.account.adapter.outbound.oauth.provider

import com.tenmm.tilserver.account.application.outbound.OAuthTokenResult
import com.tenmm.tilserver.account.application.outbound.OAuthUserInfoResult
import com.tenmm.tilserver.account.domain.OAuthType

interface OAuthProvider {
    fun getToken(authorizeCode: String): OAuthTokenResult
    fun getUserInfo(
        accessToken: String,
        refreshToken: String,
        idToken: String? = null,
    ): OAuthUserInfoResult

    fun getProvider(): OAuthType
}
