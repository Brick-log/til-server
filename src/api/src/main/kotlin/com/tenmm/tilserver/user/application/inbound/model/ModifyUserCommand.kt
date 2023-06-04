package com.tenmm.tilserver.user.application.inbound.model

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.common.domain.Url

data class ModifyUserCommand(
    val userIdentifier: Identifier,
    val path: String,
    val name: String,
    val introduction: String,
    val categoryIdentifier: Identifier,
    val mailAgreement: Boolean,
    val profileImgSrc: Url,
    val blogs: List<Url>,
)
