package com.tenmm.tilserver.draft.adapter.inbound.controller

import com.tenmm.tilserver.auth.domain.UserAuthInfo
import com.tenmm.tilserver.common.adapter.inbound.rest.model.ErrorResponse
import com.tenmm.tilserver.draft.adapter.inbound.controller.model.SyncDraftRequest
import com.tenmm.tilserver.draft.adapter.inbound.controller.model.SyncDraftResponse
import com.tenmm.tilserver.draft.application.inbound.SyncDraftUseCase
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import com.tenmm.tilserver.common.domain.Identifier

@RestController
@RequestMapping("/v1/my/draft")
@Tag(name = "Draft")
class SyncDraftController(
    private val syncDraftUseCase: SyncDraftUseCase,
) {
    @PutMapping("/sync")
    @Operation(
        summary = "드래프트 자동 동기화",
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "드래프트 자동 동기화 성공"
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
    fun syncDraft(
        userAuthInfo: UserAuthInfo,
        @RequestBody syncDraftRequest: SyncDraftRequest,
    ): SyncDraftResponse {
        syncDraftUseCase.syncByUser(userAuthInfo.userIdentifier, syncDraftRequest.data)
        return SyncDraftResponse(true)
    }
}
