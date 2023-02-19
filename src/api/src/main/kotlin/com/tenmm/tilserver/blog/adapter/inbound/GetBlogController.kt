package com.tenmm.tilserver.blog.adapter.inbound

import com.tenmm.tilserver.blog.adapter.inbound.model.GetBlogResponse
import com.tenmm.tilserver.blog.application.inbound.GetUserBlogUseCase
import com.tenmm.tilserver.common.domain.Identifier
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/blogs")
class GetBlogController(
    private val getUserBlogUseCase: GetUserBlogUseCase
) {

    @GetMapping("/{userId}")
    fun getBlogs(@PathVariable userId: String): GetBlogResponse {
        return GetBlogResponse(
            getUserBlogUseCase.getAllByUserIdentifier(Identifier(userId))
        )
    }
}
