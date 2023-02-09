package com.tenmm.tilserver.post.adapter.inbound.rest

import com.tenmm.tilserver.post.adapter.inbound.rest.model.DeletePostResponse
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/post")
class DeletePostController {
    @DeleteMapping("/{postId}")
    fun deletePost(
        @PathVariable postId: String,
    ): DeletePostResponse {
        return DeletePostResponse(true)
    }
}
