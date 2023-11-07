package com.tenmm.tilserver.retrospect.adapter.inbound

import com.tenmm.tilserver.security.domain.UserAuthInfo
import com.tenmm.tilserver.common.exception.ErrorResponse
import com.tenmm.tilserver.common.security.annotation.RequiredAuthentication
import com.tenmm.tilserver.retrospect.application.inbound.GetRetrospectUseCase
import com.tenmm.tilserver.retrospect.adapter.inbound.Model.GetRetrospectResponseModel

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag

import org.springframework.web.bind.annotation.RestController

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@RestController
@RequestMapping("/v1/my/retrospect")
@Tag(name = "Retrospect")
class GetRetrospectController(
    private val getRetrospectUseCase: GetRetrospectUseCase,
) {
    @GetMapping
    @Operation(
        summary = "Retrospect 조회",
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
    suspend fun getRetrospect(userAuthInfo: UserAuthInfo): GetRetrospectResponseModel {
        return getRetrospectUseCase.getRetrospect(userAuthInfo.userIdentifier)
    }
}
