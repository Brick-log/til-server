package com.tenmm.tilserver.blog.adapter.inbound

import com.tenmm.tilserver.blog.adapter.inbound.model.DeleteBlogResponse
import com.tenmm.tilserver.blog.application.inbound.DeleteBlogUseCase
import com.tenmm.tilserver.common.adapter.inbound.rest.model.ErrorResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/blogs")
@Tag(name = "Blog")
class DeleteBlogController(
    private val deleteBlogUseCase: DeleteBlogUseCase,
) {
    @DeleteMapping("/{blogIdentifier}")
    @Operation(
        summary = "나의 블로그 삭제",
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "나의 블로그 삭제 성공"
            ),
            ApiResponse(
                responseCode = "400",
                description = "잘못된 블로그 삭제 요청 (ex.잘못된 blog id)",
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
    fun deleteBlog(@PathVariable blogIdentifier: String): DeleteBlogResponse {
        /**
         val command = DeleteBlogCommand(
         userIdentifier = Identifier.generate(),
         blogIdentifier = Identifier(blogIdentifier)
         )
         return DeleteBlogResponse(deleteBlogUseCase.delete(command).isSuccess)
         */
        return DeleteBlogResponse(true)
    }
}
