package com.tenmm.tilserver.blog.adapter.inbound

import com.tenmm.tilserver.blog.adapter.inbound.model.ModifyBlogRequest
import com.tenmm.tilserver.blog.adapter.inbound.model.ModifyBlogResponse
import com.tenmm.tilserver.blog.application.inbound.ModifyBlogUseCase
import com.tenmm.tilserver.blog.application.inbound.model.ModifyBlogCommand
import com.tenmm.tilserver.common.domain.Identifier
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/blogs")
class ModifyBlogController(
    private val modifyBlogUseCase: ModifyBlogUseCase,
) {

    @PatchMapping("/{blogIdentifier}")
    fun modifyBlog(
        @PathVariable blogIdentifier: Identifier,
        @RequestBody modifyBlogRequest: ModifyBlogRequest,
    ): ModifyBlogResponse {
        val command = ModifyBlogCommand(
            url = modifyBlogRequest.url,
            userIdentifier = Identifier("mockUserIdentifier"),
            blogIdentifier = blogIdentifier
        )

        return ModifyBlogResponse(
            modifyBlogUseCase.modify(command).isSuccess
        )
    }
}
