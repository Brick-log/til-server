package com.tenmm.tilserver.blog.adapter.inbound

import com.tenmm.tilserver.blog.adapter.inbound.model.DeleteBlogResponse
import com.tenmm.tilserver.blog.application.inbound.DeleteBlogUseCase
import com.tenmm.tilserver.blog.application.inbound.model.DeleteBlogCommand
import com.tenmm.tilserver.common.domain.Identifier
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/blogs")
class DeleteBlogController(
    private val deleteBlogUseCase: DeleteBlogUseCase
) {

    @DeleteMapping("/{blogId}")
    fun deleteBlog(@PathVariable blogId: String): DeleteBlogResponse {
        val command = DeleteBlogCommand(
            userIdentifier = Identifier("mockUserIdentifier"),
            blogIdentifier = Identifier(blogId)
        )

        return DeleteBlogResponse(deleteBlogUseCase.delete(command).isSuccess)
    }
}
