package com.tenmm.tilserver.post.adapter.outbound

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.common.domain.Url
import com.tenmm.tilserver.common.domain.toIdentifier
import com.tenmm.tilserver.outbound.persistence.entity.ParsedPostEntity as PersistenceParsedPostEntity
import com.tenmm.tilserver.outbound.persistence.entity.PostEntity
import com.tenmm.tilserver.outbound.redis.entity.ParsedPostEntity as RedisParsedPostEntity
import com.tenmm.tilserver.post.application.outbound.model.ParsedPostResult
import com.tenmm.tilserver.post.domain.Post

fun Post.toEntity(): PostEntity {
    return PostEntity(
        identifier = this.identifier.value,
        categoryIdentifier = this.categoryIdentifier.value,
        userIdentifier = this.userIdentifier.value,
        url = this.url.value,
        title = this.title,
        description = this.description,
        hitCount = this.hitCount.toInt(),
        createdAt = this.createdAt
    )
}

fun PostEntity.toModel(): Post {
    return Post(
        identifier = Identifier(this.identifier),
        categoryIdentifier = Identifier(this.categoryIdentifier),
        userIdentifier = Identifier(this.userIdentifier),
        url = Url(this.url),
        title = this.title,
        description = this.description,
        hitCount = this.hitCount.toBigInteger(),
        createdAt = this.createdAt
    )
}

fun RedisParsedPostEntity.toResult(): ParsedPostResult {
    return ParsedPostResult(
        identifier = Identifier(this.identifier),
        userIdentifier = Identifier(this.userIdentifier),
        url = Url(this.url),
        title = this.title,
        description = this.description,
        createdAt = this.createdAt
    )
}
fun PersistenceParsedPostEntity.toResult(): ParsedPostResult {
    return ParsedPostResult(
        identifier = this.identifier.toIdentifier(),
        userIdentifier = this.userIdentifier.toIdentifier(),
        url = Url(this.url),
        title = this.title,
        description = this.description,
        createdAt = this.createdAt,
    )
}
