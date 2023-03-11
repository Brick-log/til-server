package com.tenmm.tilserver.post.adapter.inbound.rest

import com.tenmm.tilserver.common.adapter.inbound.model.ErrorResponse
import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.post.adapter.inbound.rest.model.ModifyRecommendedPostResponse
import com.tenmm.tilserver.post.application.inbound.AddRecommendedPostUseCase
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/admin/recommended/post")
@Tag(name = "Recommended Post")
class AddRecommendedPostController(
    private val addRecommendedPostUseCase: AddRecommendedPostUseCase,
) {
    @PostMapping("/{postIdentifier}")
    @Operation(
        summary = "추천 포스트 등록",
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "추천 포스트 등록 성공"
            ),
            ApiResponse(
                responseCode = "400",
                description = "잘못된 포스트 업로드 등록 요청 (ex.잘못된 id)",
                content = [Content(schema = Schema(implementation = ErrorResponse::class))]
            ),
            ApiResponse(
                responseCode = "401",
                description = "로그인 하지 않은 사용자",
                content = [Content(schema = Schema(implementation = ErrorResponse::class))]
            ),
            ApiResponse(
                responseCode = "403",
                description = "접근 권한이 없는 사용자",
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
    fun addRecommendedPost(
        @PathVariable postIdentifier: String,
    ): ModifyRecommendedPostResponse {
        val result = addRecommendedPostUseCase.addByPostId(Identifier(postIdentifier))
        return ModifyRecommendedPostResponse.fromResult(result)
    }
}
