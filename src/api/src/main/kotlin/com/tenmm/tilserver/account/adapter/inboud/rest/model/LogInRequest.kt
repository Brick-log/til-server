package com.tenmm.tilserver.account.adapter.inboud.rest.model

import com.tenmm.tilserver.account.domain.OAuthType

data class LogInRequest(
    val token: String,
    val type: OAuthType,
    val redirectUrl: String
)
