package com.tenmm.tilserver.blog.application.inbound.model

import com.tenmm.tilserver.common.domain.Identifier

data class DeleteBlogCommand(
    val userIdentifier: Identifier,
    val blogIdentifier: Identifier
)
