package com.tenmm.tilserver.post.adapter.inbound.rest

import com.tenmm.tilserver.common.adapter.inbound.rest.model.ErrorResponse
import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.post.adapter.inbound.rest.model.ModifyPostRequest
import com.tenmm.tilserver.post.adapter.inbound.rest.model.ModifyPostResponse
import com.tenmm.tilserver.post.application.inbound.ModifyPostUseCase
import com.tenmm.tilserver.post.application.inbound.model.ModifyPostCommand
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/post")
@Tag(name = "Post")
class ModifyPostController(
    private val modifyPostUseCase: ModifyPostUseCase,
) {
    @PutMapping("/{postIdentifier}")
    @Operation(
        summary = "포스트 변경",
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "포스트 변경 성공"
            ),
            ApiResponse(
                responseCode = "400",
                description = "잘못된 포스트 변경 요청 (ex.잘못된 post id)",
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
    fun modifyPost(
        @PathVariable postIdentifier: String,
        @RequestBody modifyPostRequest: ModifyPostRequest,
    ): ModifyPostResponse {
        val command = ModifyPostCommand(
            identifier = Identifier(postIdentifier),
            title = modifyPostRequest.title,
            summary = modifyPostRequest.summary,
            createdAt = modifyPostRequest.createdAt,
        )
        val result = modifyPostUseCase.modifyByIdentifier(command)
        return ModifyPostResponse(result.isSuccess)
    }
}
