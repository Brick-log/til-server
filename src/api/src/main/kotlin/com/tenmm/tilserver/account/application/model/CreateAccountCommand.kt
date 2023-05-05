package com.tenmm.tilserver.account.application.model

import com.tenmm.tilserver.account.domain.OAuthType
import com.tenmm.tilserver.common.domain.Email
import com.tenmm.tilserver.common.domain.Identifier

data class CreateAccountCommand(
    val email: Email,
    val type: OAuthType,
    val userIdentifier: Identifier,
)
