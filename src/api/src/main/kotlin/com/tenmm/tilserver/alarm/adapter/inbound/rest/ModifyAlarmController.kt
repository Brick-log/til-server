package com.tenmm.tilserver.alarm.adapter.inbound.rest

import com.tenmm.tilserver.alarm.adapter.inbound.rest.model.ModifyAlarmRequest
import com.tenmm.tilserver.alarm.adapter.inbound.rest.model.ModifyAlarmResponse
import com.tenmm.tilserver.alarm.application.inbound.ModifyAlarmUseCase
import com.tenmm.tilserver.alarm.application.inbound.model.ModifyAlarmCommand
import com.tenmm.tilserver.auth.domain.UserAuthInfo
import com.tenmm.tilserver.common.exception.ErrorResponse
import com.tenmm.tilserver.common.security.annotation.RequiredAuthentication
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@Tag(name = "Alarm")
@RequestMapping("/v1/my/notification")
class ModifyAlarmController(
    private val modifyAlarmUseCase: ModifyAlarmUseCase,
) {
    @PutMapping
    @Operation(
        summary = "나의 알람 수정하기",
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "알람 수정 성공",
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
    @RequiredAuthentication
    fun modifyAlarm(
        userAuthInfo: UserAuthInfo,
        @RequestBody modifyAlarmRequest: ModifyAlarmRequest,
    ): ModifyAlarmResponse {
        return ModifyAlarmResponse(
            modifyAlarmUseCase.modifyAlarm(
                ModifyAlarmCommand(
                    userIdentifier = userAuthInfo.userIdentifier,
                    enable = modifyAlarmRequest.enable,
                    iteration = modifyAlarmRequest.iteration,
                )
            ).isSuccess
        )
    }
}
