package com.tenmm.tilserver.post.domain

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.common.domain.Url
import com.tenmm.tilserver.user.domain.User
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
    fun setUserInfo(user: User): PostWithUserInfo {
        return PostWithUserInfo(
            identifier = identifier,
            userPath = user.path,
            userProfileSrc = user.thumbnailUrl,
            categoryIdentifier = categoryIdentifier,
            title = title,
            description = description,
            url = url,
            createdAt = createdAt,
            hitCount = hitCount
        )
    }
}

data class PostWithUserInfo(
    val identifier: Identifier,
    val userPath: String,
    val userProfileSrc: Url,
    val categoryIdentifier: Identifier,
    val title: String,
    val description: String,
    val url: Url,
    val createdAt: Timestamp,
    val hitCount: BigInteger,
)
