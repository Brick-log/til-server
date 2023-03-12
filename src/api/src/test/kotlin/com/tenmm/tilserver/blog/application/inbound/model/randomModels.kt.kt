package com.tenmm.tilserver.blog.application.inbound.model

import com.tenmm.tilserver.common.domain.Identifier

fun randomDeleteBlogCommand(
    userIdentifier: Identifier = Identifier.generate(),
    blogIdentifier: Identifier = Identifier.generate()
): DeleteBlogCommand {
    return DeleteBlogCommand(
        userIdentifier = userIdentifier,
        blogIdentifier = blogIdentifier
    )
}
