package com.tenmm.tilserver.user.application.outbound.model

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.common.domain.Url

data class CreateUserRequest(
    val name: String,
    val userIdentifier: Identifier,
    val profileImgSrc: Url,
)
