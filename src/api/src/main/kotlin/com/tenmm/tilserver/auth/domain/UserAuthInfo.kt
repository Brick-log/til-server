package com.tenmm.tilserver.auth.domain

import com.tenmm.tilserver.common.domain.Identifier

data class UserAuthInfo(
    val userIdentifier: Identifier,
)
