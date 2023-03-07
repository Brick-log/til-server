package com.tenmm.tilserver.draft.adapter.inbound.controller

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.draft.adapter.inbound.controller.model.SaveDraftRequest
import com.tenmm.tilserver.draft.adapter.inbound.controller.model.SaveDraftResponse
import com.tenmm.tilserver.draft.application.inbound.SaveDraftUseCase
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
// swagger
import io.swagger.v3.oas.annotations.tags.Tag
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema

@RestController
@Tag(name = "draft")
@RequestMapping("/v1/my/draft")
class SaveDraftController(
    private val saveDraftUseCase: SaveDraftUseCase,
) {
    @PostMapping
    @Operation(
        summary = "draft save api",
        description = "draft작성 내용을 저장하기 위한 api",
        responses = [
            ApiResponse(responseCode = "200", description = "draft 저장 성공"),
            ApiResponse(responseCode = "400", description = "잘못된 draft 저장 요청(ex.잘못된 draft id)", content = [Content(schema = Schema(hidden = true))]),
            ApiResponse(responseCode = "500", description = "서버 내부 오류로 인한 draft 저장 실패", content = [Content(schema = Schema(hidden = true))])
        ]
    )
    fun save(
        @RequestBody saveDraftRequest: SaveDraftRequest,
    ): SaveDraftResponse {
        // saveDraftUseCase.save(
        //     Identifier.generate(),
        //     saveDraftRequest.data
        // )

        // Mock data 입력 테스트용 출력 코드
        println("Identifier : ${Identifier.generate()} data: ${saveDraftRequest.data}")

        return SaveDraftResponse(true)
    }
}
