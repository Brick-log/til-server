package com.tenmm.tilserver.account.application.model

import com.tenmm.tilserver.account.domain.OAuthType

data class LogInCommand(
    val authorizeCode: String,
    val type: OAuthType,
)
