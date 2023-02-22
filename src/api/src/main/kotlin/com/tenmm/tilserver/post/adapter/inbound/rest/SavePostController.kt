package com.tenmm.tilserver.post.adapter.inbound.rest

import com.tenmm.tilserver.post.adapter.inbound.rest.model.ConfirmUploadPostRequest
import com.tenmm.tilserver.post.adapter.inbound.rest.model.ConfirmUploadPostResponse
import com.tenmm.tilserver.post.adapter.inbound.rest.model.RequestUploadPostRequest
import com.tenmm.tilserver.post.adapter.inbound.rest.model.RequestUploadPostResponse
import com.tenmm.tilserver.post.application.inbound.SavePostUseCase
import com.tenmm.tilserver.post.application.inbound.model.PostSaveConfirmCommand
import com.tenmm.tilserver.post.application.inbound.model.PostSaveRequestCommand
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/post/upload")
class SavePostController(
    private val savePostUseCase: SavePostUseCase,
) {
    @PostMapping("/request")
    fun uploadRequest(
        @RequestBody requestUploadPostRequest: RequestUploadPostRequest,
    ): RequestUploadPostResponse {
        val requestCommand = PostSaveRequestCommand(
            requestUploadPostRequest.userIdentifier,
            requestUploadPostRequest.url
        )
        val result = savePostUseCase.requestSave(requestCommand)
        return RequestUploadPostResponse.fromResult(result)
    }

    @PostMapping("/confirm")
    fun uploadConfirm(
        @RequestBody confirmUploadPostRequest: ConfirmUploadPostRequest,
    ): ConfirmUploadPostResponse {
        val confirmCommand = PostSaveConfirmCommand(
            saveIdentifier = confirmUploadPostRequest.saveIdentifier,
            title = confirmUploadPostRequest.title,
            description = confirmUploadPostRequest.description,
            createdAt = confirmUploadPostRequest.createdAt,
        )
        val result = savePostUseCase.confirmSave(confirmCommand)
        return ConfirmUploadPostResponse.fromResult(result)
    }
}
