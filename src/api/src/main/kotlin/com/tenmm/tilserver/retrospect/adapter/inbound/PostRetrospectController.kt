package com.tenmm.tilserver.retrospect.adapter.inbound
import com.tenmm.tilserver.security.domain.UserAuthInfo
import com.tenmm.tilserver.common.exception.ErrorResponse
import com.tenmm.tilserver.common.security.annotation.RequiredAuthentication
import com.tenmm.tilserver.retrospect.adapter.inbound.Model.PostRetrospectRequestModel

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag

import org.springframework.web.bind.annotation.RestController

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestBody
import com.tenmm.tilserver.retrospect.application.inbound.PostRetrospectUseCase

@RestController
@RequestMapping("/v1/my/retrospect")
@Tag(name = "Retrospect")
class PostRetrospectController(
    private val postRetrospectUseCase: PostRetrospectUseCase,
) {
    @PostMapping
    @Operation(
        summary = "Retrospect 등록",
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "성공"
            ),
            ApiResponse(
                responseCode = "401",
                description = "로그인 하지 않은 사용자",
                content = [Content(schema = Schema(implementation = ErrorResponse::class))]
            ),
            ApiResponse(
                responseCode = "500",
                description = "서버에러",
                content = [Content(schema = Schema(implementation = ErrorResponse::class))]
            )
        ]
    )
    @RequiredAuthentication
    suspend fun postRetrospect(userAuthInfo: UserAuthInfo, @RequestBody postRetrospectRequestModel: PostRetrospectRequestModel) {
        print("postRetrospectRequestModel: $postRetrospectRequestModel")
        print("userAuthInfo: $userAuthInfo")
        return postRetrospectUseCase.postRetrospect(userAuthInfo.userIdentifier, postRetrospectRequestModel)
    }
}
