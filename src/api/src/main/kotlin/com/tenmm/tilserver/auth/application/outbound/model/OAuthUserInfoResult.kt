package com.tenmm.tilserver.auth.application.outbound.model

import com.tenmm.tilserver.common.domain.Email
import com.tenmm.tilserver.common.domain.Url

data class OAuthUserInfoResult(
    val name: String,
    val profileImgUrl: Url,
    val email: Email
)
