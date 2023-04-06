package com.tenmm.tilserver.draft.adapter.inbound.controller

import com.tenmm.tilserver.auth.domain.UserAuthInfo
import com.tenmm.tilserver.common.adapter.inbound.rest.model.ErrorResponse
import com.tenmm.tilserver.draft.adapter.inbound.controller.model.GetDraftResponse
import com.tenmm.tilserver.draft.adapter.inbound.controller.model.toResponse
import com.tenmm.tilserver.draft.application.inbound.GetDraftUseCase
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/my/draft")
@Tag(name = "Draft")
class GetDraftController(
    private val getDraftUseCase: GetDraftUseCase,
) {
    @GetMapping
    @Operation(
        summary = "드래프트 조회",
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "성공"
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
    fun getDraft(userAuthInfo: UserAuthInfo): GetDraftResponse {
        return getDraftUseCase.getByUserIdentifier(userAuthInfo.userIdentifier).toResponse()
    }
}
