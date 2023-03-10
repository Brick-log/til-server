package com.tenmm.tilserver.blog.adapter.inbound

import com.tenmm.tilserver.blog.adapter.inbound.model.GetBlogResponse
import com.tenmm.tilserver.blog.application.inbound.GetUserBlogUseCase
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/blogs")
@Tag(name = "Blog")
class GetBlogController(
    private val getUserBlogUseCase: GetUserBlogUseCase,
) {
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{name}")
    @Operation(
        summary = "나의 블로그 삭제",
        responses = [
            ApiResponse(responseCode = "200", description = "사용자 이름으로 모든 블로그 조회하기"),
            ApiResponse(responseCode = "400", description = "잘못된 블로그 조회 요청 (ex.잘못된 사용자 이름)", content = [Content(schema = Schema(hidden = true))]),
            ApiResponse(responseCode = "500", description = "서버에러", content = [Content(schema = Schema(hidden = true))])
        ]
    )
    fun getBlogs(@PathVariable name: String): GetBlogResponse {
        return GetBlogResponse(
            getUserBlogUseCase.getAllByUserName(name)
        )
    }
}
