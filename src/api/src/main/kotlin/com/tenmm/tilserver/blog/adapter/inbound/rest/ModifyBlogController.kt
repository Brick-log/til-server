package com.tenmm.tilserver.blog.adapter.inbound.rest

import com.tenmm.tilserver.auth.domain.UserAuthInfo
import com.tenmm.tilserver.blog.adapter.inbound.rest.model.ModifyBlogRequest
import com.tenmm.tilserver.blog.adapter.inbound.rest.model.ModifyBlogResponse
import com.tenmm.tilserver.blog.application.inbound.ModifyBlogUseCase
import com.tenmm.tilserver.blog.application.inbound.model.ModifyBlogCommand
import com.tenmm.tilserver.common.adapter.inbound.rest.model.ErrorResponse
import com.tenmm.tilserver.common.domain.Url
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/blogs")
@Tag(name = "Blog")
class ModifyBlogController(
    private val modifyBlogUseCase: ModifyBlogUseCase,
) {

    @PutMapping
    @Operation(
        summary = "나의 블로그 수정",
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "나의 블로그 수정 성공"
            ),
            ApiResponse(
                responseCode = "400",
                description = "잘못된 블로그 수정 요청 (ex.잘못된 blog id)",
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
                description = "블로그 조회 실패",
                content = [Content(schema = Schema(implementation = ErrorResponse::class))]
            ),
            ApiResponse(
                responseCode = "500",
                description = "서버에러",
                content = [Content(schema = Schema(implementation = ErrorResponse::class))]
            )
        ]
    )
    fun modifyBlog(
        userAuthInfo: UserAuthInfo,
        @RequestBody modifyBlogRequest: ModifyBlogRequest,
    ): ModifyBlogResponse {
        val command = ModifyBlogCommand(
            urls = modifyBlogRequest.infoList.map { Url(it.url) },
            userIdentifier = userAuthInfo.userIdentifier,
        )

        return ModifyBlogResponse(
            modifyBlogUseCase.modify(command).isSuccess
        )
    }
}
