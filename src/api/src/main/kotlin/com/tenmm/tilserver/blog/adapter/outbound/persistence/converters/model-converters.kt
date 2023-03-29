package com.tenmm.tilserver.blog.adapter.outbound.persistence.converters

import com.tenmm.tilserver.blog.domain.Blog
import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.common.domain.Url
import com.tenmm.tilserver.outbound.persistence.entity.BlogEntity

fun BlogEntity.toModel(): Blog {
    return Blog(
        identifier = Identifier(this.blogIdentifier),
        userIdentifier = Identifier(this.userIdentifier),
        url = Url(this.url)
    )
}

fun Blog.toEntity(): BlogEntity {
    return BlogEntity(
        blogIdentifier = this.identifier.value,
        userIdentifier = this.userIdentifier.value,
        url = this.url.value
    )
}
