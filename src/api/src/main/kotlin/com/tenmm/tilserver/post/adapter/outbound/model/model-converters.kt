package com.tenmm.tilserver.post.adapter.outbound.model

import com.tenmm.tilserver.post.application.outbound.model.ParsedPostResult
import com.tenmm.tilserver.outbound.persistence.entity.ParsedPostEntity
import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.common.domain.Url

fun ParsedPostEntity.toResult(): ParsedPostResult {
    return ParsedPostResult(
        identifier = Identifier(this.identifier),
        userIdentifier = Identifier(this.userIdentifier),
        url = Url(this.url),
        title = this.title,
        description = this.description,
        createdAt = this.createdAt
    )
}
