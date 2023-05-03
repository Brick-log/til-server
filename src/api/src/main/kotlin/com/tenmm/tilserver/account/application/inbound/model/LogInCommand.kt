package com.tenmm.tilserver.account.application.inbound.model

import com.tenmm.tilserver.account.domain.OAuthType

data class LogInCommand(
    val authorizeCode: String,
    val type: OAuthType,
)
