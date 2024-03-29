package com.tenmm.tilserver.blog.adapter.inbound.rest

import com.tenmm.tilserver.blog.adapter.inbound.rest.model.BlogResult
import com.tenmm.tilserver.blog.adapter.inbound.rest.model.GetBlogResponse
import com.tenmm.tilserver.blog.application.inbound.GetUserBlogUseCase
import com.tenmm.tilserver.common.exception.ErrorResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/blogs")
@Tag(name = "Blog")
class GetBlogController(
    private val getUserBlogUseCase: GetUserBlogUseCase,
) {
    @GetMapping("/{path}")
    @Operation(
        summary = "나의 블로그 가져오기",
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "사용자 이름으로 모든 블로그 조회하기"
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
    fun getBlogs(@PathVariable path: String): GetBlogResponse {
        val blogList = getUserBlogUseCase.getAllByUserPath(path)
        val blogResults = blogList.map {
            BlogResult(
                it.blogIdentifier.value,
                it.url.value
            )
        }
        return GetBlogResponse(blogResults)
    }
}
