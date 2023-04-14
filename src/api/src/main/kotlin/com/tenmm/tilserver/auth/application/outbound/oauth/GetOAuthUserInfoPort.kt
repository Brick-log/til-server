package com.tenmm.tilserver.auth.application.outbound.oauth

import com.tenmm.tilserver.auth.application.outbound.oauth.model.OAuthUserInfoResult
import com.tenmm.tilserver.auth.domain.OAuthType

interface GetOAuthUserInfoPort {
    fun getUserInfo(
        accessToken: String,
        refreshToken: String,
        idToken: String?,
        type: OAuthType
    ): OAuthUserInfoResult
}
