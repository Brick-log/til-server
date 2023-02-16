package com.tenmm.tilserver.alaram.adapter.inbound

import com.tenmm.tilserver.alaram.adapter.inbound.model.ModifyAlarmRequest
import com.tenmm.tilserver.alaram.adapter.inbound.model.ModifyAlarmResponse
import com.tenmm.tilserver.alaram.application.inbound.ModifyAlarmUsecase
import com.tenmm.tilserver.alaram.application.inbound.model.ModifiyAlarmModel
import com.tenmm.tilserver.common.domain.Identifier
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/v1/my/settings/notification")
class ModifyAlarmController(
    private val modifyAlarmUsecase: ModifyAlarmUsecase
) {
    @PatchMapping("")
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
