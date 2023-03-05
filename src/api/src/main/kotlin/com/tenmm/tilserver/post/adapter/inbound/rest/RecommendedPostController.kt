package com.tenmm.tilserver.post.adapter.inbound.rest

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.post.adapter.inbound.rest.model.ModifyRecommendedPostResponse
import com.tenmm.tilserver.post.application.inbound.AddRecommendedPostUseCase
import com.tenmm.tilserver.post.application.inbound.DeleteRecommendedPostUseCase
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/admin/recommended/post")
class RecommendedPostController(
    private val addRecommendedPostUseCase: AddRecommendedPostUseCase,
    private val deleteRecommendedPostUseCase: DeleteRecommendedPostUseCase,
) {
    @PostMapping("/{postIdentifier}")
    fun addRecommendedPost(
        @PathVariable postIdentifier: Identifier,
    ): ModifyRecommendedPostResponse {
//        val result = addRecommendedPostUseCase.addByPostId(postIdentifier)
//        return ModifyRecommendedPostResponse.fromResult(result)
        return ModifyRecommendedPostResponse(true)
    }

    @DeleteMapping("/{postIdentifier}")
    fun deleteRecommendedPost(
        @PathVariable postIdentifier: Identifier,
    ): ModifyRecommendedPostResponse {
//        val result = deleteRecommendedPostUseCase.deleteByPostId(postIdentifier)
//        return ModifyRecommendedPostResponse.fromResult(result)
        return ModifyRecommendedPostResponse(true)
    }
}
