package com.tenmm.tilserver.post.domain

import com.tenmm.tilserver.category.domain.Category
import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.common.domain.Url
import com.tenmm.tilserver.user.domain.User
import java.math.BigInteger
import java.sql.Timestamp

data class Post(
    val identifier: Identifier,
    val userIdentifier: Identifier,
    val categoryIdentifier: String,
    val title: String,
    val description: String,
    val url: Url,
    val createdAt: Timestamp,
    val hitCount: BigInteger,
)

data class PostDetail(
    val identifier: Identifier,
    val userPath: String,
    val userName: String,
    val userProfileSrc: Url,
    val categoryIdentifier: String,
    val categoryName: String,
    val title: String,
    val description: String,
    val url: Url,
    val createdAt: Timestamp,
    val hitCount: BigInteger,
) {
    companion object {
        fun generate(post: Post, user: User, category: Category): PostDetail {
            return PostDetail(
                identifier = post.identifier,
                userPath = user.path,
                userName = user.name,
                userProfileSrc = user.thumbnailUrl,
                categoryIdentifier = category.identifier,
                categoryName = category.name,
                title = post.title,
                description = post.description,
                url = post.url,
                createdAt = post.createdAt,
                hitCount = post.hitCount
            )
        }
    }
}
