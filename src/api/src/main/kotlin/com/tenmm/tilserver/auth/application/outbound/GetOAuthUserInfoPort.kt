package com.tenmm.tilserver.auth.application.outbound

import com.tenmm.tilserver.auth.application.outbound.model.OAuthUserInfoResult
import com.tenmm.tilserver.auth.domain.OAuthType

interface GetOAuthUserInfoPort{
    fun getUserInfo(
        accessToken:String,
        refreshToken:String,
        idToken: String?,
        type: OAuthType
    ): OAuthUserInfoResult

}