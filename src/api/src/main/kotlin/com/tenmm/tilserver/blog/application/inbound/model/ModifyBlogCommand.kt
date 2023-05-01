package com.tenmm.tilserver.blog.application.inbound.model

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.common.domain.Url

data class ModifyBlogCommand(
    val urls: List<Url>,
    val userIdentifier: Identifier,
)
