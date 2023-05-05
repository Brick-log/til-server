package com.tenmm.tilserver.post.adapter.inbound.rest.model

import com.tenmm.tilserver.post.application.inbound.model.GetPostListResult
import com.tenmm.tilserver.post.domain.PostWithUserInfo
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
    val userPath: String,
    val userProfileSrc: String,
    val categoryIdentifier: String,
    val title: String,
    val description: String,
    val url: String,
    val createdAt: Timestamp,
    val hitCount: BigInteger,
) {
    companion object {
        fun fromDomain(post: PostWithUserInfo): PostResponse {
            return PostResponse(
                identifier = post.identifier.value,
                userPath = post.userPath,
                userProfileSrc = post.userProfileSrc.value,
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
