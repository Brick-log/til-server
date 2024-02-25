package com.tenmm.tilserver.retrospect.adapter.inbound

import com.tenmm.tilserver.security.domain.UserAuthInfo
import com.tenmm.tilserver.common.exception.ErrorResponse
import com.tenmm.tilserver.common.security.annotation.RequiredAuthentication

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag

import org.springframework.web.bind.annotation.RestController

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

import com.tenmm.tilserver.retrospect.application.inbound.DeleteRetrospectUseCase
import com.tenmm.tilserver.retrospect.adapter.inbound.Model.DeleteRetrospectResponseModel

import com.tenmm.tilserver.common.domain.Identifier

@RestController
@RequestMapping("/v1/my/retrospect")
@Tag(name = "Retrospect(my)")
class DeleteRetrospectController(
    private val deleteRetrospectUseCase: DeleteRetrospectUseCase,
) {
    @DeleteMapping("{retrospectIdentifier}")
    @Operation(
        summary = "본인 회고 삭제",
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
    suspend fun deleteRetrospect(userAuthInfo: UserAuthInfo, @PathVariable retrospectIdentifier: String): DeleteRetrospectResponseModel {
        deleteRetrospectUseCase.deleteRetrospect(userAuthInfo.userIdentifier, Identifier(retrospectIdentifier))
        return DeleteRetrospectResponseModel(message = "success")
    }
}
