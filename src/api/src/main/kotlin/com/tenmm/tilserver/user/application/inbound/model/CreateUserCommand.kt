package com.tenmm.tilserver.user.application.inbound.model

import com.tenmm.tilserver.common.domain.Url

data class CreateUserCommand(
    val name: String,
    val introduction: String,
    val thumbnailUrl: Url,
)
