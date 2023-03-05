package com.tenmm.tilserver.post.application.inbound

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.post.application.inbound.model.GetPostListResult
import com.tenmm.tilserver.post.application.inbound.model.GetPostMetaResult
import com.tenmm.tilserver.post.application.inbound.model.GetPostResult
import com.tenmm.tilserver.post.domain.Post
import java.sql.Timestamp

interface GetPostUseCase {
    fun showPostByIdentifier(postIdentifier: Identifier): GetPostResult
    fun getPostByIdentifier(postIdentifier: Identifier): Post
    fun getPostListByIdentifiers(postIdentifiers: List<Identifier>): List<Post>

    fun getPostListRandom(): GetPostListResult
    fun getPostListByCategory(categoryIdentifier: Identifier): GetPostListResult
    fun getPostListByNameAndDate(
        name: String,
        to: Timestamp,
        from: Timestamp,
        size: Long,
    ): GetPostListResult

    fun getPostListByNameAndDateWithPageToken(
        name: String,
        to: Timestamp,
        from: Timestamp,
        size: Long,
        pageToken: String,
    ): GetPostListResult

    fun getPostMetaListByNameAndDate(name: String, to: Timestamp, from: Timestamp): GetPostMetaResult
    fun getPostCountByMonth(userIdentifier: Identifier, month: Int): Int
}
