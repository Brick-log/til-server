package com.tenmm.tilserver.account.application.inbound

import com.tenmm.tilserver.account.application.model.OAuthUserInfo
import com.tenmm.tilserver.account.domain.OAuthType

interface GetOAuthUserInfoUseCase {
    val oAuthType: OAuthType
    fun getByOAuthToken(oAuthToken: String): OAuthUserInfo
}
