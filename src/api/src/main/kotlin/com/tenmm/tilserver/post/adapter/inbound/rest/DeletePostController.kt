package com.tenmm.tilserver.post.adapter.inbound.rest

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.post.adapter.inbound.rest.model.DeletePostResponse
import com.tenmm.tilserver.post.application.inbound.DeletePostUseCase
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

// @RestController
@RequestMapping("/v1/post")
class DeletePostController(
    private val deletePostUseCase: DeletePostUseCase,
) {
    @DeleteMapping("/{postIdentifier}")
    fun deletePost(
        @PathVariable postIdentifier: Identifier,
    ): DeletePostResponse {
        val deleteResult = deletePostUseCase.deleteByIdentifier(postIdentifier)
        return DeletePostResponse.fromResult(deleteResult)
    }
}
