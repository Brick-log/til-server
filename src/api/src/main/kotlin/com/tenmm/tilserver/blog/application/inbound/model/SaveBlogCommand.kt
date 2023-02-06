package com.tenmm.tilserver.blog.application.inbound.model

import com.tenmm.tilserver.common.domain.Identifier

data class SaveBlogCommand(
    val identifier: Identifier,
    val url: String
)
