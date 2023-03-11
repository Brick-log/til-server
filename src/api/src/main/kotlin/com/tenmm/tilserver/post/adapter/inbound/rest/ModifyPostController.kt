package com.tenmm.tilserver.post.adapter.inbound.rest

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.post.adapter.inbound.rest.model.ModifyPostRequest
import com.tenmm.tilserver.post.adapter.inbound.rest.model.ModifyPostResponse
import com.tenmm.tilserver.post.application.inbound.ModifyPostUseCase
import com.tenmm.tilserver.post.application.inbound.model.ModifyPostCommand
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/post")
class ModifyPostController(
    private val modifyPostUseCase: ModifyPostUseCase,
) {
    @PatchMapping("/{postIdentifier}")
    fun modifyPost(
        @PathVariable postIdentifier: Identifier,
        @RequestBody modifyPostRequest: ModifyPostRequest,
    ): ModifyPostResponse {
        val command = ModifyPostCommand(
            identifier = postIdentifier,
            title = modifyPostRequest.title,
            summary = modifyPostRequest.summary,
            createdAt = modifyPostRequest.createdAt,
        )
        modifyPostUseCase.modifyByIdentifier(command)
        return ModifyPostResponse(true)
    }
}
