package com.tenmm.tilserver.user.application.inbound.model

import com.tenmm.tilserver.common.domain.Identifier

data class CreateUserCommand(
    val name: String,
    val userIdentifier: Identifier,
)
