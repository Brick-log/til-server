package com.tenmm.tilserver.auth.adapter.inboud.rest.model

import com.tenmm.tilserver.auth.domain.OAuthType

data class LogInRequest(
    val token: String,
    val type: OAuthType
)
