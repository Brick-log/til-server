package com.tenmm.tilserver.blog.adapter.inbound

import com.tenmm.tilserver.blog.adapter.inbound.model.SaveBlogRequest
import com.tenmm.tilserver.blog.adapter.inbound.model.SaveBlogResponse
import com.tenmm.tilserver.blog.application.inbound.SaveBlogUseCase
import com.tenmm.tilserver.blog.application.inbound.model.SaveBlogCommand
import com.tenmm.tilserver.common.domain.Identifier
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

    /**
     * UserIdentifer는 Token에 있는거?
     */
    @PostMapping
    fun saveBlog(@RequestBody saveBlogRequest: SaveBlogRequest): SaveBlogResponse {
        val command = SaveBlogCommand(
            url = saveBlogRequest.url,
            userIdentifier = Identifier.generate(),
            blogIdentifier = Identifier.generate()
        )
        return SaveBlogResponse(
            saveBlogUseCase.save(command).isSuccess
        )
    }
}
