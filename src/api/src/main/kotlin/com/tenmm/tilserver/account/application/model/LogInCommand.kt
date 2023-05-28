package com.tenmm.tilserver.account.application.model

import com.tenmm.tilserver.account.domain.OAuthType
import com.tenmm.tilserver.common.domain.Url

data class LogInCommand(
    val authorizeCode: String,
    val type: OAuthType,
    val redirectUri: Url
)
