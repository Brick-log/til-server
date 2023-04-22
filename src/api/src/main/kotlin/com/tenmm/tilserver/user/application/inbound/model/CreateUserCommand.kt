package com.tenmm.tilserver.user.application.inbound.model

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.common.domain.Url

data class CreateUserCommand(
    val name: String,
    val userIdentifier: Identifier,
    val introduction: String = "",
    val thumbnailUrl: Url = Url(""),
)
