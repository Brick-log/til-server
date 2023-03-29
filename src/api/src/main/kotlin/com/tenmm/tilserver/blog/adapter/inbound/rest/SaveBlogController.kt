package com.tenmm.tilserver.blog.adapter.inbound.rest

import com.tenmm.tilserver.blog.adapter.inbound.rest.model.SaveBlogRequest
import com.tenmm.tilserver.blog.adapter.inbound.rest.model.SaveBlogResponse
import com.tenmm.tilserver.blog.application.inbound.SaveBlogUseCase
import com.tenmm.tilserver.blog.application.inbound.model.SaveBlogCommand
import com.tenmm.tilserver.common.adapter.inbound.rest.model.ErrorResponse
import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.common.domain.Url
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
@RequestMapping("/v1/blogs")
@Tag(name = "Blog")
class SaveBlogController(
    private val saveBlogUseCase: SaveBlogUseCase,
) {

    @PostMapping
    @Operation(
        summary = "나의 블로그 저장",
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "나의 블로그 삭제 성공"
            ),
            ApiResponse(
                responseCode = "400",
                description = "잘못된 블로그 저장 요청",
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
    fun saveBlog(@RequestBody saveBlogRequest: SaveBlogRequest): SaveBlogResponse {

        val command = SaveBlogCommand(
            url = Url(saveBlogRequest.url),
            userIdentifier = Identifier.generate(),
            blogIdentifier = Identifier.generate()
        )
        return SaveBlogResponse(
            saveBlogUseCase.save(command).isSuccess
        )
    }
}
