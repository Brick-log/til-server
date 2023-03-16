package com.tenmm.tilserver.alarm.adapter.inbound.rest

import com.tenmm.tilserver.alarm.adapter.inbound.rest.model.GetAlarmResponse
import com.tenmm.tilserver.alarm.application.inbound.GetAlarmUseCase
import com.tenmm.tilserver.common.adapter.inbound.model.ErrorResponse
import com.tenmm.tilserver.common.domain.Identifier
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import java.util.UUID
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@Tag(name = "Alarm")
@RequestMapping("/v1/my/notification")
class GetAlarmController(
    private val getAlarmUseCase: GetAlarmUseCase,
) {
    @GetMapping
    @Operation(
        summary = "나의 알람 가져오기",
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "나의 알람 조회 성공",
            ),
            ApiResponse(
                responseCode = "401",
                description = "로그인 하지 않은 사용자",
                content = [Content(schema = Schema(implementation = ErrorResponse::class))]
            ),
            ApiResponse(
                responseCode = "403",
                description = "접근 권한이 없는 사용자",
                content = [Content(schema = Schema(implementation = ErrorResponse::class))]
            ),
            ApiResponse(
                responseCode = "500",
                description = "서버 에러",
                content = [Content(schema = Schema(implementation = ErrorResponse::class))]
            )
        ]
    )
    fun getAlarm(): GetAlarmResponse {
        // TODO: userIdentifier 토큰에서 가져오도록 수정
        /**
        val alarm = getAlarmUseCase.getAlarmByUserId(Identifier(UUID.randomUUID().toString()))
        return GetAlarmResponse(
            userIdentifier = alarm.userIdentifier.value,
            enable = alarm.enable,
            iteration = alarm.iteration
        )
        */

        return GetAlarmResponse(
            enable = true,
            iteration = "* * * * *"
        )
    }
}
