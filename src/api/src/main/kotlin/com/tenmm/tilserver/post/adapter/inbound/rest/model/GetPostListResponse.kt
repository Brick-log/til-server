package com.tenmm.tilserver.post.adapter.inbound.rest.model

import com.tenmm.tilserver.post.application.inbound.model.GetPostListResult
import com.tenmm.tilserver.post.domain.Post
import java.math.BigInteger
import java.sql.Timestamp
import org.apache.logging.log4j.util.Strings

data class GetPostListResponse(
    val postList: List<PostResponse>,
    val size: Int,
    val nextPageToken: String,
) {
    companion object {
        fun fromResult(result: GetPostListResult): GetPostListResponse {
            return GetPostListResponse(
                postList = result.posts.map { PostResponse.fromDomain(it) },
                size = result.size,
                nextPageToken = result.nextPageToken ?: Strings.EMPTY,
            )
        }
    }
}

data class PostResponse(
    val identifier: String,
    val userIdentifier: String,
    val categoryIdentifier: String,
    val title: String,
    val description: String,
    val url: String,
    val createdAt: Timestamp,
    val hitCount: BigInteger,
) {
    companion object {
        fun fromDomain(post: Post): PostResponse {
            return PostResponse(
                identifier = post.identifier.value,
                userIdentifier = post.userIdentifier.value,
                categoryIdentifier = post.categoryIdentifier.value,
                title = post.title,
                description = post.description,
                url = post.url.value,
                createdAt = post.createdAt,
                hitCount = post.hitCount
            )
        }
    }
}
