package com.tenmm.tilserver.post.adapter.inbound.rest

import com.tenmm.tilserver.common.adapter.inbound.model.ErrorResponse
import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.post.adapter.inbound.rest.model.GetPostListResponse
import com.tenmm.tilserver.post.adapter.inbound.rest.model.GetPostMetaResponse
import com.tenmm.tilserver.post.adapter.inbound.rest.model.GetPostResponse
import com.tenmm.tilserver.post.application.inbound.GetPostUseCase
import com.tenmm.tilserver.post.application.inbound.GetRecommendedPostUseCase
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import java.sql.Timestamp
import java.time.Instant
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/post")
@Tag(name = "Post")
class GetPostController(
    private val getPostUseCase: GetPostUseCase,
    private val getRecommendedPostUseCase: GetRecommendedPostUseCase,
) {
    @GetMapping("/{postIdentifier}")
    @Operation(
        summary = "포스트 주소 요청",
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "포스트 주소 요청 성공"
            ),
            ApiResponse(
                responseCode = "400",
                description = "잘못된 포스트 요청 (ex.잘못된 id)",
                content = [Content(schema = Schema(implementation = ErrorResponse::class))]
            ),
            ApiResponse(
                responseCode = "404",
                description = "포스트 조회 실패",
                content = [Content(schema = Schema(implementation = ErrorResponse::class))]
            ),
            ApiResponse(
                responseCode = "500",
                description = "서버에러",
                content = [Content(schema = Schema(implementation = ErrorResponse::class))]
            )
        ]
    )
    fun getPostByIdentifier(
        @PathVariable postIdentifier: String,
    ): GetPostResponse {
        val post = getPostUseCase.showPostByIdentifier(Identifier(postIdentifier))
        return GetPostResponse.fromResult(post)
    }

    @GetMapping("/user/{path}")
    @Operation(
        summary = "사용자 포스트 리스트 요청",
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "포스트 리스트 요청 성공"
            ),
            ApiResponse(
                responseCode = "400",
                description = "잘못된 포스트 요청 (ex.잘못된 path)",
                content = [Content(schema = Schema(implementation = ErrorResponse::class))]
            ),
            ApiResponse(
                responseCode = "404",
                description = "유저 path 조회 실패",
                content = [Content(schema = Schema(implementation = ErrorResponse::class))]
            ),
            ApiResponse(
                responseCode = "500",
                description = "서버에러",
                content = [Content(schema = Schema(implementation = ErrorResponse::class))]
            )
        ]
    )
    fun getPostByPath(
        @PathVariable path: String,
        @RequestParam to: Long,
        @RequestParam from: Long,
        @RequestParam size: Int,
        @RequestParam(required = false) pageToken: String? = null,
    ): GetPostListResponse {
        val postListResult = getPostUseCase.getPostListByNameAndDateWithPageToken(
            path = path,
            to = Timestamp.from(Instant.ofEpochMilli(to)),
            from = Timestamp.from(Instant.ofEpochMilli(from)),
            size = size,
            pageToken = pageToken
        )
        return GetPostListResponse.fromResult(postListResult)
    }

    @GetMapping("/user/{path}/meta")
    @Operation(
        summary = "사용자 포스트 잔디 기록 요청",
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "포스트 리스트 요청 성공"
            ),
            ApiResponse(
                responseCode = "400",
                description = "잘못된 포스트 메타 요청 (ex.잘못된 path)",
                content = [Content(schema = Schema(implementation = ErrorResponse::class))]
            ),
            ApiResponse(
                responseCode = "404",
                description = "유저 path 조회 실패",
                content = [Content(schema = Schema(implementation = ErrorResponse::class))]
            ),
            ApiResponse(
                responseCode = "500",
                description = "서버에러",
                content = [Content(schema = Schema(implementation = ErrorResponse::class))]
            )
        ]
    )
    fun getPostMetaByName(
        @PathVariable path: String,
        @RequestParam to: Long,
        @RequestParam from: Long,
    ): GetPostMetaResponse {
        val postMetaResult = getPostUseCase.getPostMetaListByNameAndDate(
            path = path,
            to = Timestamp.from(Instant.ofEpochMilli(to)),
            from = Timestamp.from(Instant.ofEpochMilli(from))
        )
        return GetPostMetaResponse.fromResult(postMetaResult)
    }

    @GetMapping("/category")
    @Operation(
        summary = "카테고리별 포스트 리스트 요청",
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "포스트 리스트 요청 성공"
            ),
            ApiResponse(
                responseCode = "400",
                description = "잘못된 포스트 리스트 요청 (ex.잘못된 카테고리 identifier)",
                content = [Content(schema = Schema(implementation = ErrorResponse::class))]
            ),
            ApiResponse(
                responseCode = "404",
                description = "유저 path 조회 실패",
                content = [Content(schema = Schema(implementation = ErrorResponse::class))]
            ),
            ApiResponse(
                responseCode = "500",
                description = "서버에러",
                content = [Content(schema = Schema(implementation = ErrorResponse::class))]
            )
        ]
    )
    fun getByCategory(
        @RequestParam(name = "id", required = false) categoryIdentifier: Identifier? = null,
        @RequestParam size: Int,
        @RequestParam(required = false) pageToken: String? = null,
    ): GetPostListResponse {
        val postListResult = if (categoryIdentifier != null) {
            getPostUseCase.getPostListByCategory(categoryIdentifier, size, pageToken)
        } else {
            getPostUseCase.getPostListRandom(size, pageToken)
        }
        return GetPostListResponse.fromResult(postListResult)
    }

    @GetMapping("/category/recommend")
    @Operation(
        summary = "카테고리별 추천 포스트 리스트 요청",
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "포스트 리스트 요청 성공"
            ),
            ApiResponse(
                responseCode = "400",
                description = "잘못된 포스트 리스트 요청 (ex.잘못된 카테고리 identifier)",
                content = [Content(schema = Schema(implementation = ErrorResponse::class))]
            ),
            ApiResponse(
                responseCode = "404",
                description = "유저 path 조회 실패",
                content = [Content(schema = Schema(implementation = ErrorResponse::class))]
            ),
            ApiResponse(
                responseCode = "500",
                description = "서버에러",
                content = [Content(schema = Schema(implementation = ErrorResponse::class))]
            )
        ]
    )
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
