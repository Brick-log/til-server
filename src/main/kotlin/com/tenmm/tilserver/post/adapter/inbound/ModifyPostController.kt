package com.tenmm.tilserver.post.adapter.inbound

import com.tenmm.tilserver.post.adapter.inbound.model.ModifyPostRequest
import com.tenmm.tilserver.post.adapter.inbound.model.ModifyPostResponse
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/post")
class ModifyPostController {
    @PatchMapping("/{postId}")
    fun modifyPost(
        @PathVariable postId: String,
        @RequestBody modifyPostRequest: ModifyPostRequest,
    ): ModifyPostResponse {
        return ModifyPostResponse(true)
    }
}
