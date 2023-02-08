package com.tenmm.tilserver.blog.application.inbound.model

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.common.domain.Url

data class SaveBlogCommand(
    val identifier: Identifier,
    val url: Url
)
