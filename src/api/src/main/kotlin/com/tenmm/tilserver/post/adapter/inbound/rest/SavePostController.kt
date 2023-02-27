package com.tenmm.tilserver.post.adapter.inbound.rest

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.post.adapter.inbound.rest.model.ConfirmUploadPostRequest
import com.tenmm.tilserver.post.adapter.inbound.rest.model.ConfirmUploadPostResponse
import com.tenmm.tilserver.post.adapter.inbound.rest.model.RequestUploadPostRequest
import com.tenmm.tilserver.post.adapter.inbound.rest.model.RequestUploadPostResponse
import com.tenmm.tilserver.post.application.inbound.SavePostUseCase
import java.sql.Timestamp
import java.time.Instant
import org.apache.commons.lang3.RandomStringUtils
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
//        val requestCommand = PostSaveRequestCommand(
//            requestUploadPostRequest.userIdentifier,
//            requestUploadPostRequest.url
//        )
//        val result = savePostUseCase.requestSave(requestCommand)
//        return RequestUploadPostResponse.fromResult(result)
        return RequestUploadPostResponse(
            saveIdentifier = Identifier.generate(),
            title = RandomStringUtils.random(10),
            description = RandomStringUtils.random(10),
            createdAt = Timestamp.from(Instant.now())
        )
    }

    @PostMapping("/confirm")
    fun uploadConfirm(
        @RequestBody confirmUploadPostRequest: ConfirmUploadPostRequest,
    ): ConfirmUploadPostResponse {
//        val confirmCommand = PostSaveConfirmCommand(
//            saveIdentifier = confirmUploadPostRequest.saveIdentifier,
//            title = confirmUploadPostRequest.title,
//            description = confirmUploadPostRequest.description,
//            createdAt = confirmUploadPostRequest.createdAt,
//        )
//        val result = savePostUseCase.confirmSave(confirmCommand)
//        return ConfirmUploadPostResponse.fromResult(result)
        return ConfirmUploadPostResponse(
            true, 10
        )
    }
}
