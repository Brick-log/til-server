package com.tenmm.tilserver.account.application.outbound

import com.tenmm.tilserver.account.domain.OAuthType
import com.tenmm.tilserver.common.domain.Email
import com.tenmm.tilserver.common.domain.Url

data class OAuthUserInfo(
    val name: String,
    val profileImgUrl: Url,
    val email: Email,
    val type: OAuthType,
)
