package com.tenmm.tilserver.post.adapter.inbound.rest

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.post.adapter.inbound.rest.model.GetPostListResponse
import com.tenmm.tilserver.post.adapter.inbound.rest.model.GetPostMetaResponse
import com.tenmm.tilserver.post.adapter.inbound.rest.model.GetPostResponse
import com.tenmm.tilserver.post.application.inbound.GetPostUseCase
import com.tenmm.tilserver.post.application.inbound.GetRecommendedPostUseCase
import java.sql.Timestamp
import java.time.Instant
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

// @RestController
@RequestMapping("/v1/post")
class GetPostController(
    private val getPostUseCase: GetPostUseCase,
    private val getRecommendedPostUseCase: GetRecommendedPostUseCase,
) {
    @GetMapping("/{postIdentifier}")
    fun getPostByIdentifier(
        @PathVariable postIdentifier: Identifier,
    ): GetPostResponse {
        val post = getPostUseCase.getPostByIdentifier(postIdentifier)
        return GetPostResponse.fromResult(post)
    }

    @GetMapping("/user/{name}")
    fun getPostByName(
        @PathVariable name: String,
        @RequestParam to: Long,
        @RequestParam from: Long,
        @RequestParam size: Long,
        @RequestParam(required = false) pageToken: String? = null,
    ): GetPostListResponse {
        val postListResult = if (pageToken != null) {
            getPostUseCase.getPostListByNameAndDateWithPageToken(
                name = name,
                to = Timestamp.from(Instant.ofEpochMilli(to)),
                from = Timestamp.from(Instant.ofEpochMilli(from)),
                size = size,
                pageToken = pageToken
            )
        } else {
            getPostUseCase.getPostListByNameAndDate(
                name = name,
                to = Timestamp.from(Instant.ofEpochMilli(to)),
                from = Timestamp.from(Instant.ofEpochMilli(from)),
                size = size,
            )
        }
        return GetPostListResponse.fromResult(postListResult)
    }

    @GetMapping("/user/{name}/meta")
    fun getPostMetaByName(
        @PathVariable name: String,
        @RequestParam to: Long,
        @RequestParam from: Long,
    ): GetPostMetaResponse {
        val postMetaResult = getPostUseCase.getPostMetaListByNameAndDate(
            name = name,
            to = Timestamp.from(Instant.ofEpochMilli(to)),
            from = Timestamp.from(Instant.ofEpochMilli(from))
        )
        return GetPostMetaResponse.fromResult(postMetaResult)
    }

    @GetMapping("/category")
    fun getByCategory(
        @RequestParam(name = "id", required = false) categoryIdentifier: Identifier? = null,
    ): GetPostListResponse {
        val postListResult = if (categoryIdentifier != null) {
            getPostUseCase.getPostListByCategory(categoryIdentifier)
        } else {
            getPostUseCase.getPostListRandom()
        }
        return GetPostListResponse.fromResult(postListResult)
    }

    @GetMapping("/category/recommend")
    fun getRecommendationList(
        @RequestParam(name = "id", required = false) categoryIdentifier: Identifier? = null,
    ): GetPostListResponse {
        val postListResult = if (categoryIdentifier != null) {
            getRecommendedPostUseCase.getRecommendedPostListByCategory(categoryIdentifier)
        } else {
            getRecommendedPostUseCase.getRecommendedPostListRandom()
        }
        return GetPostListResponse.fromResult(postListResult)
    }
}
