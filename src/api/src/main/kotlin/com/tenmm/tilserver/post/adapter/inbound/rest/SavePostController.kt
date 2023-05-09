package com.tenmm.tilserver.post.adapter.inbound.rest

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.common.domain.Url
import com.tenmm.tilserver.common.exception.ErrorResponse
import com.tenmm.tilserver.common.security.annotation.RequiredAuthentication
import com.tenmm.tilserver.post.adapter.inbound.rest.model.ConfirmUploadPostRequest
import com.tenmm.tilserver.post.adapter.inbound.rest.model.ConfirmUploadPostResponse
import com.tenmm.tilserver.post.adapter.inbound.rest.model.RequestUploadPostRequest
import com.tenmm.tilserver.post.adapter.inbound.rest.model.RequestUploadPostResponse
import com.tenmm.tilserver.post.application.inbound.SavePostUseCase
import com.tenmm.tilserver.post.application.inbound.model.PostSaveConfirmCommand
import com.tenmm.tilserver.post.application.inbound.model.PostSaveRequestCommand
import com.tenmm.tilserver.security.domain.UserAuthInfo
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/post/upload")
@Tag(name = "Post")
class SavePostController(
    private val savePostUseCase: SavePostUseCase,
) {
    @PostMapping("/request")
    @Operation(
        summary = "포스트 업로드 요청",
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "포스트 업로드 요청 성공"
            ),
            ApiResponse(
                responseCode = "400",
                description = "잘못된 포스트 업로드 요청 (ex.잘못된 url)",
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
                responseCode = "500",
                description = "서버에러",
                content = [Content(schema = Schema(implementation = ErrorResponse::class))]
            )
        ]
    )
    @RequiredAuthentication
    suspend fun uploadRequest(
        userAuthInfo: UserAuthInfo,
        @RequestBody requestUploadPostRequest: RequestUploadPostRequest,
    ): RequestUploadPostResponse {
        val requestCommand = PostSaveRequestCommand(
            userAuthInfo.userIdentifier,
            Url(requestUploadPostRequest.url)
        )
        val result = savePostUseCase.requestSave(requestCommand)
        return RequestUploadPostResponse.fromResult(result)
    }

    @PostMapping("/confirm")
    @Operation(
        summary = "포스트 업로드 확인",
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "포스트 업로드 확인 성공"
            ),
            ApiResponse(
                responseCode = "400",
                description = "잘못된 포스트 업로드 확인 요청 (ex.잘못된 url)",
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
                description = "포스트 업로드 요청 기록 조회 실패",
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
    suspend fun uploadConfirm(
        userAuthInfo: UserAuthInfo,
        @RequestBody confirmUploadPostRequest: ConfirmUploadPostRequest,
    ): ConfirmUploadPostResponse {
        val confirmCommand = PostSaveConfirmCommand(
            userIdentifier = userAuthInfo.userIdentifier,
            saveIdentifier = Identifier(confirmUploadPostRequest.identifier),
            title = confirmUploadPostRequest.title,
            description = confirmUploadPostRequest.summary,
            createdAt = confirmUploadPostRequest.createdAt,
        )
        val result = savePostUseCase.confirmSave(confirmCommand)
        return ConfirmUploadPostResponse.fromResult(result)
    }
}
