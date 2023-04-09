package com.tenmm.tilserver.auth.adapter.outbound

import com.tenmm.tilserver.auth.application.outbound.model.OAuthTokenResult
import com.tenmm.tilserver.auth.application.outbound.model.OAuthUserInfoResult
import com.tenmm.tilserver.auth.domain.OAuthType
import org.springframework.security.oauth2.client.registration.ClientRegistration

interface OAuthClient {
    fun getToken(registration: ClientRegistration, authorizeCode: String): OAuthTokenResult
    fun getUserInfo(
        accessToken: String,
        refreshToken: String,
        idToken: String? = null,
        registration: ClientRegistration
    ): OAuthUserInfoResult

    fun getProvider(): OAuthType
    fun getAuthorizationCodeRedirectURI(registration: ClientRegistration): String
}
