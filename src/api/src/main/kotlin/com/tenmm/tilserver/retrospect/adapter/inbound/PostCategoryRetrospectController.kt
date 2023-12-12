package com.tenmm.tilserver.retrospect.adapter.inbound

import com.tenmm.tilserver.retrospect.application.inbound.PostCategoryRetrospectUseCase

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import com.tenmm.tilserver.common.exception.ErrorResponse

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

import com.tenmm.tilserver.common.domain.Identifier

@RestController
@RequestMapping("/v1/retrospect/category")
@Tag(name = "Retrospect")
class PostCategoryRetrospectController(
    private val postCategoryRetrospectUseCase: PostCategoryRetrospectUseCase,
) {
    @PostMapping("/recommend")
    @Operation(
        summary = "카테고리별 추천 회고 업로드",
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "추천 회고 업도르 요청 성공"
            ),
            ApiResponse(
                responseCode = "400",
                description = "잘못된 추천 회고 업로드 요청 (ex.잘못된 카테고리 identifier)",
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
    fun postRecommendationList(
        @RequestParam(name = "retrospectIdentifier") retrospectIdentifier: String,
        @RequestParam(name = "categoryIdentifier") categoryIdentifier: String
    ): Boolean {
        return postCategoryRetrospectUseCase.addByRetrospectId(Identifier(retrospectIdentifier), Identifier(categoryIdentifier))
    }
}
