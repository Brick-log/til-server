package com.tenmm.tilserver.auth.application.outbound.model

import com.tenmm.tilserver.common.domain.Email

data class OAuthUserInfoResult(
    val email: Email
)
