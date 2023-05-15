package com.tenmm.tilserver.post.application.outbound

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.common.domain.ResultWithToken
import com.tenmm.tilserver.post.domain.Post
import java.sql.Timestamp

interface GetPostPort {
    fun getPostRandom(size: Int): List<Post>
    fun getPostByIdentifier(postIdentifier: Identifier): Post?
    fun getPostListByIdentifiers(postIdentifiers: List<Identifier>): List<Post>

    fun getPostListByCategoryIdentifier(categoryIdentifier: Identifier, size: Int): ResultWithToken<List<Post>>
    fun getPostListByCategoryIdentifierWithPageToken(size: Int, pageToken: String): ResultWithToken<List<Post>>

    fun getPostListByUserAndCreatedAt(
        userIdentifier: Identifier,
        to: Timestamp,
        from: Timestamp,
        size: Int,
    ): ResultWithToken<List<Post>>

    fun getPostListByUserAndCreatedAtWithPageToken(
        userIdentifier: Identifier,
        size: Int,
        pageToken: String,
    ): ResultWithToken<List<Post>>

    fun getPostListByCreatedAt(
        userIdentifier: Identifier,
        to: Timestamp,
        from: Timestamp,
    ): List<Post>

    fun countByUserIdentifierAndMonth(userIdentifier: Identifier, year: Int, month: Int): Int
    fun totalPostCountByUser(userIdentifier: Identifier): Int
    fun totalPostCountByCategory(categoryIdentifier: Identifier): Int
}
