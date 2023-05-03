package com.tenmm.tilserver.security.domain

import com.tenmm.tilserver.common.domain.Identifier

data class UserAuthInfo(
    val userIdentifier: Identifier,
)
