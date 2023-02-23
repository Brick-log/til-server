package com.tenmm.tilserver.blog.adapter.inbound

import com.tenmm.tilserver.blog.adapter.inbound.model.GetBlogResponse
import com.tenmm.tilserver.blog.application.inbound.GetUserBlogUseCase
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/blogs")
class GetBlogController(
    private val getUserBlogUseCase: GetUserBlogUseCase,
) {
    @GetMapping("/{name}")
    fun getBlogs(@PathVariable name: String): GetBlogResponse {
        return GetBlogResponse(
            getUserBlogUseCase.getAllByUserName(name)
        )
    }
}
