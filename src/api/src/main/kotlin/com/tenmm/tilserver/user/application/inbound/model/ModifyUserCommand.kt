package com.tenmm.tilserver.user.application.inbound.model

import com.tenmm.tilserver.common.domain.Identifier

data class ModifyUserCommand(
    val userIdentifier: Identifier,
    val path: String,
    val name: String,
    val introduction: String,
    val categoryIdentifier: Identifier,
)
