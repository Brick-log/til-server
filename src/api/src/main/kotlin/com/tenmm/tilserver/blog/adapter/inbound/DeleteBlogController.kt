package com.tenmm.tilserver.blog.adapter.inbound

import com.tenmm.tilserver.blog.adapter.inbound.model.DeleteBlogResponse
import com.tenmm.tilserver.blog.application.inbound.DeleteBlogUseCase
import com.tenmm.tilserver.blog.application.inbound.model.DeleteBlogCommand
import com.tenmm.tilserver.common.domain.Identifier
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/blogs")
@Tag(name = "Blog", description = "Delete Blog")
class DeleteBlogController(
    private val deleteBlogUseCase: DeleteBlogUseCase,
) {

    @DeleteMapping("/{blogIdentifier}")
    fun deleteBlog(@PathVariable blogIdentifier: Identifier): DeleteBlogResponse {
        val command = DeleteBlogCommand(
            userIdentifier = Identifier.generate(),
            blogIdentifier = blogIdentifier
        )

        return DeleteBlogResponse(deleteBlogUseCase.delete(command).isSuccess)
    }
}
