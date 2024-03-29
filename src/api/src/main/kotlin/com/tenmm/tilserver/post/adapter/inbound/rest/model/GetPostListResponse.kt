package com.tenmm.tilserver.post.adapter.inbound.rest.model

import com.tenmm.tilserver.post.application.inbound.model.GetPostListResult
import com.tenmm.tilserver.post.domain.PostDetail
import java.math.BigInteger
import java.sql.Timestamp

data class GetPostListResponse(
    val posts: List<PostResponse>,
    val size: Int,
    val nextPageToken: String?,
) {
    companion object {
        fun fromResult(result: GetPostListResult): GetPostListResponse {
            return GetPostListResponse(
                posts = result.posts.map { PostResponse.fromDomain(it) },
                size = result.size,
                nextPageToken = result.nextPageToken
            )
        }
    }
}

data class PostResponse(
    val identifier: String,
    val userPath: String,
    val userName: String,
    val profileImgSrc: String,
    val categoryIdentifier: String,
    val categoryName: String,
    val title: String,
    val summary: String,
    val url: String,
    val createdAt: Timestamp,
    val hitCount: BigInteger,
) {
    companion object {
        fun fromDomain(post: PostDetail): PostResponse {
            return PostResponse(
                identifier = post.identifier.value,
                userPath = post.userPath,
                userName = post.userName,
                profileImgSrc = post.userProfileSrc.value,
                categoryIdentifier = post.categoryIdentifier,
                categoryName = post.categoryName,
                title = post.title,
                summary = post.description,
                url = post.url.value,
                createdAt = post.createdAt,
                hitCount = post.hitCount
            )
        }
    }
}
