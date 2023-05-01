package com.tenmm.tilserver.post.domain

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.common.domain.Url
import java.math.BigInteger
import java.sql.Timestamp

data class Post(
    val identifier: Identifier,
    val userIdentifier: Identifier,
    val categoryIdentifier: Identifier,
    val title: String,
    val description: String,
    val url: Url,
    val createdAt: Timestamp,
    val hitCount: BigInteger,
) {
    fun setUserPath(userPath: String): PostWithUserPath {
        return PostWithUserPath(
            identifier = identifier,
            userPath = userPath,
            categoryIdentifier = categoryIdentifier,
            title = title,
            description = description,
            url = url,
            createdAt = createdAt,
            hitCount = hitCount
        )
    }
}

data class PostWithUserPath(
    val identifier: Identifier,
    val userPath: String,
    val categoryIdentifier: Identifier,
    val title: String,
    val description: String,
    val url: Url,
    val createdAt: Timestamp,
    val hitCount: BigInteger,
)
