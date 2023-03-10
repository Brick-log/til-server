package com.tenmm.tilserver.draft.adapter.inbound.controller

import com.tenmm.tilserver.draft.adapter.inbound.controller.model.GetDraftResponse
import java.time.LocalDateTime
import java.time.ZoneOffset
// import org.apache.commons.lang3.RandomStringUtils
import org.springframework.web.bind.annotation.GetMapping
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
class GetDraftController {
    @GetMapping
    @Operation(
        summary = "draft get api",
        description = "draft작성 내용을 가져오기 위한 api, 자동저장한 draft가 있을 경우 해당 데이터를 반환",
        responses = [
            ApiResponse(responseCode = "200", description = "draft 조회 성공"),
            ApiResponse(responseCode = "400", description = "잘못된 draft 조회 요청(ex.잘못된 draft id)", content = [Content(schema = Schema(hidden = true))]),
            ApiResponse(responseCode = "500", description = "서버 내부 오류로 인한 draft 조회 실패", content = [Content(schema = Schema(hidden = true))])
        ]
    )
    fun getDraft(): GetDraftResponse {
        return GetDraftResponse(
            data = "Mock data from [/v1/my/draft]",
            updatedAt = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)
        )
    }
}
