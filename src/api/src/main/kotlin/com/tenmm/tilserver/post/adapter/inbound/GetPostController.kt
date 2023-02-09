package com.tenmm.tilserver.post.adapter.inbound

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.post.adapter.inbound.model.GetPostListResponse
import com.tenmm.tilserver.post.adapter.inbound.model.GetPostMetaResponse
import com.tenmm.tilserver.post.adapter.inbound.model.GetPostResponse
import com.tenmm.tilserver.post.application.inbound.GetPostUseCase
import com.tenmm.tilserver.post.application.inbound.GetRecommendedPostUseCase
import java.sql.Timestamp
import java.time.Instant
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

//@RestController
@RequestMapping("/v1/posts")
class GetPostController(
    private val getPostUseCase: GetPostUseCase,
    private val getRecommendedPostUseCase: GetRecommendedPostUseCase,
) {
    @GetMapping("/{postIdValue}")
    fun getPostByIdentifier(
        @PathVariable postIdValue: String,
    ): GetPostResponse {
        val postIdentifier = Identifier(postIdValue)
        val post = getPostUseCase.getPostByIdentifier(postIdentifier)
        return GetPostResponse.fromResult(post)
    }

    @GetMapping("/user/{name}")
    fun getPostByName(
        @PathVariable name: String,
        @RequestParam(required = false) to: Long? = null,
        @RequestParam(required = false) from: Long? = null,
        @RequestParam(required = false) pageToken: String? = null,
    ): GetPostListResponse {
        val postListResult = if (pageToken != null) {
            getPostUseCase.getPostListByPageToken(pageToken)
        } else {
            if (to != null && from != null) {
                getPostUseCase.getPostListByNameAndDate(
                    name = name,
                    to = Timestamp.from(Instant.ofEpochMilli(to)),
                    from = Timestamp.from(Instant.ofEpochMilli(from))
                )
            } else {
                throw IllegalArgumentException("Request post list parameter wrong to = $to, from = $from")
            }
        }
        return GetPostListResponse.fromResult(postListResult)
    }

    @GetMapping("/user/{name}/meta")
    fun getPostMetaByName(
        @PathVariable name: String,
        @RequestParam(required = false) to: Long? = null,
        @RequestParam(required = false) from: Long? = null,
    ): GetPostMetaResponse {
        val postMetaResult = if (to != null && from != null) {
            getPostUseCase.getPostMetaListByNameAndDate(
                name = name,
                to = Timestamp.from(Instant.ofEpochMilli(to)),
                from = Timestamp.from(Instant.ofEpochMilli(from))
            )
        } else {
            throw IllegalArgumentException("Request post list parameter wrong to = $to, from = $from")
        }
        return GetPostMetaResponse.fromResult(postMetaResult)
    }

    @GetMapping("/category/{categoryId}")
    fun getByCategory(
        @PathVariable categoryId: String,
    ): GetPostListResponse {
        val categoryIdentifier = Identifier(categoryId)
        val postListResult = getPostUseCase.getPostListByCategory(categoryIdentifier)
        return GetPostListResponse.fromResult(postListResult)
    }

    @GetMapping("/category/{categoryId}/recommend")
    fun getRecommendationList(
        @PathVariable categoryId: String,
    ): GetPostListResponse {
        val categoryIdentifier = Identifier(categoryId)
        val postListResult = getRecommendedPostUseCase.getRecommendedPostListByCategory(categoryIdentifier)
        return GetPostListResponse.fromResult(postListResult)
    }
}
