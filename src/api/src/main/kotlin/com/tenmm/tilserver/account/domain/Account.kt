package com.tenmm.tilserver.account.domain

import com.tenmm.tilserver.common.domain.Email
import com.tenmm.tilserver.common.domain.Identifier

data class Account(
    val email: Email,
    val oAuthType: OAuthType,
    val status: AccountStatus,
    val userIdentifier: Identifier,
    val isSpamNotificationAgreed: Boolean,
)
