package com.tenmm.tilserver.retrospect.adapter.inbound

import com.tenmm.tilserver.retrospect.application.inbound.GetCategoryRetrospectUseCase

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import com.tenmm.tilserver.common.exception.ErrorResponse

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

import com.tenmm.tilserver.retrospect.adapter.inbound.Model.GetUserRetrospectResponseModel

import com.tenmm.tilserver.security.domain.UserAuthInfo
import com.tenmm.tilserver.common.security.annotation.OptionalAuthentication

@RestController
@RequestMapping("/v1/retrospect/category")
@Tag(name = "Retrospect")
class GetCategoryRetrospectController(
    private val getCategoryRetrospectUseCase: GetCategoryRetrospectUseCase,
) {

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
    @OptionalAuthentication
    suspend fun getByCategory(
        userAuthInfo: UserAuthInfo?,
        @RequestParam size: Int,
        @RequestParam(name = "retrospectType") retrospectType: String,
        @RequestParam(required = false) pageToken: String? = null,
    ): GetUserRetrospectResponseModel {
        // val retrospectListResult = if (pageToken != null) {
        //     getCategoryRetrospectUseCase.getRetrospectListByTypeWithPageToken(
        //         pageToken = pageToken,
        //         categoryIdentifier = categoryIdentifier,
        //         size = size
        //     )
        // } else {
        //     getCategoryRetrospectUseCase.getRetrospectListByType(
        //         categoryIdentifier = categoryIdentifier,
        //         size = size
        //     )
        // }

        // return GetRetrospectListResponse.fromResult(retrospectListResult)
        return if (pageToken != null) {
            getCategoryRetrospectUseCase.getRetrospectListByTypeWithPageToken(
                pageToken = pageToken,
                retrospectType = retrospectType,
                size = size,
                userIdentifier = userAuthInfo?.userIdentifier
            )
        } else {
            getCategoryRetrospectUseCase.getRetrospectListByType(
                retrospectType = retrospectType,
                size = size,
                userIdentifier = userAuthInfo?.userIdentifier
            )
        }
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
    @OptionalAuthentication
    fun getRecommendationList(
        userAuthInfo: UserAuthInfo?,
        @RequestParam(name = "identifier") categoryIdentifier: String
    ): String {
        // val searchCategoryIdentifier = if (userAuthInfo != null) {
        //     getUserUseCase.getByIdentifier(userAuthInfo.userIdentifier).categoryIdentifier
        // } else {
        //     categoryIdentifier
        // }

        // val retrospectListResult = if (searchCategoryIdentifier != null && searchCategoryIdentifier != "all") {
        //     getRecommendedRetrospectUseCase.getRecommendedRetrospectListByCategory(searchCategoryIdentifier)
        // } else {
        //     getRecommendedRetrospectUseCase.getRecommendedRetrospectListRandom()
        // }
        // return GetRetrospectListResponse.fromResult(retrospectListResult)
        return ""
    }
}
