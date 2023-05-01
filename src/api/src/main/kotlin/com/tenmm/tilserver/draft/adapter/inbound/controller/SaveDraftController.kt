package com.tenmm.tilserver.draft.adapter.inbound.controller

import com.tenmm.tilserver.auth.domain.UserAuthInfo
import com.tenmm.tilserver.common.adapter.inbound.rest.model.ErrorResponse
import com.tenmm.tilserver.common.security.annotation.RequiredAuthentication
import com.tenmm.tilserver.draft.adapter.inbound.controller.model.SaveDraftRequest
import com.tenmm.tilserver.draft.adapter.inbound.controller.model.SaveDraftResponse
import com.tenmm.tilserver.draft.application.inbound.SaveDraftUseCase
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/my/draft")
@Tag(name = "Draft")
class SaveDraftController(
    private val saveDraftUseCase: SaveDraftUseCase,
) {
    @PostMapping
    @Operation(
        summary = "드래프트 수동 저장",
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "드래프트 수동 저장 성공"
            ),
            ApiResponse(
                responseCode = "401",
                description = "로그인 하지 않은 사용자",
                content = [Content(schema = Schema(implementation = ErrorResponse::class))]
            ),
            ApiResponse(
                responseCode = "500",
                description = "서버에러",
                content = [Content(schema = Schema(implementation = ErrorResponse::class))]
            )
        ]
    )
    @RequiredAuthentication
    fun save(
        userAuthInfo: UserAuthInfo,
        @RequestBody saveDraftRequest: SaveDraftRequest,
    ): SaveDraftResponse {
        saveDraftUseCase.saveByUserIdentifier(
            userAuthInfo.userIdentifier,
            saveDraftRequest.data
        )
        return SaveDraftResponse(true)
    }
}
