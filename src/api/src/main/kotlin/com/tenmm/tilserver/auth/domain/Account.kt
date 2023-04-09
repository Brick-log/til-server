package com.tenmm.tilserver.auth.domain

import com.tenmm.tilserver.common.domain.Email
import com.tenmm.tilserver.common.domain.Identifier

data class Account(
    val name: String,
    val email: Email,
    val oAuthType: OAuthType,
    val status: AccountStatus,
    val userIdentifier: Identifier,
    val isSpamNotificationAgreed: Boolean,
)
