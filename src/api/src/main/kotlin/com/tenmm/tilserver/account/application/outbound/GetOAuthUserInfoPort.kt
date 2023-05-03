package com.tenmm.tilserver.account.application.outbound

import com.tenmm.tilserver.account.domain.OAuthType

interface GetOAuthUserInfoPort {
    fun getUserInfo(
        accessToken: String,
        refreshToken: String,
        idToken: String?,
        type: OAuthType
    ): OAuthUserInfoResult
}