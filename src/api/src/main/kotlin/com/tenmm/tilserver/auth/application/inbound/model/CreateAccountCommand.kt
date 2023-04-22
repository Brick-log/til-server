package com.tenmm.tilserver.auth.application.inbound.model

import com.tenmm.tilserver.auth.domain.OAuthType
import com.tenmm.tilserver.common.domain.Email
import com.tenmm.tilserver.common.domain.Identifier

data class CreateAccountCommand(
    val email: Email,
    val type: OAuthType,
    val userIdentifier: Identifier,
)
