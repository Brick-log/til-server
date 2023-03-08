package com.tenmm.tilserver.alaram.adapter.inbound

import com.tenmm.tilserver.alaram.adapter.inbound.model.ModifyAlarmRequest
import com.tenmm.tilserver.alaram.adapter.inbound.model.ModifyAlarmResponse
import com.tenmm.tilserver.alaram.application.inbound.ModifyAlarmUsecase
import com.tenmm.tilserver.alaram.application.inbound.model.ModifiyAlarmModel
import com.tenmm.tilserver.common.domain.Identifier
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
// TODO: userIdentifier 토큰에서 가져오도록 수정 되면 아래 import 제거
import java.util.UUID
import org.springframework.web.bind.annotation.PutMapping

@RestController
@Tag(name = "alarm")
@RequestMapping("/v1/my/notification")
class ModifyAlarmController(
    private val modifyAlarmUsecase: ModifyAlarmUsecase
) {
    @PutMapping
    @Operation(
        summary = "나의 알람 수정하기",
        responses = [
            ApiResponse(responseCode = "200", description = "알람 수정 성공", content = [Content(schema = Schema(hidden = true))]),
            ApiResponse(responseCode = "401", description = "로그인 하지 않은 사용자", content = [Content(schema = Schema(hidden = true))]),
            ApiResponse(responseCode = "403", description = "접근 권한이 없는 사용자", content = [Content(schema = Schema(hidden = true))]),
            ApiResponse(responseCode = "500", description = "서버 에러", content = [Content(schema = Schema(hidden = true))])
        ]
    )
    fun modifyAlarm(
        @RequestBody modifyAlarmRequest: ModifyAlarmRequest,
    ): ModifyAlarmResponse {
        return ModifyAlarmResponse(
            modifyAlarmUsecase.modifyAlarm(
                ModifiyAlarmModel(
                    // TODO: userIdentifier 토큰에서 가져오도록 수정
                    userIdentifier = Identifier(UUID.randomUUID().toString()),
                    enable = modifyAlarmRequest.enable,
                    iteration = modifyAlarmRequest.iteration,
                )
            )
        )
    }
}
