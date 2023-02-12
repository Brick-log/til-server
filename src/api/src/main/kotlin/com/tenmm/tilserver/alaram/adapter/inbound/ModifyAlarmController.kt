package com.tenmm.tilserver.alaram.adapter.inbound

import com.tenmm.tilserver.alaram.adapter.inbound.model.ModifyAlarmRequest
import com.tenmm.tilserver.alaram.adapter.inbound.model.ModifyAlarmResponse
import com.tenmm.tilserver.alaram.application.inbound.ModifyAlarmUsecase
import com.tenmm.tilserver.alaram.application.inbound.model.ModifiyAlarmModel
import com.tenmm.tilserver.common.domain.Identifier
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/my/settings/notification")
class ModifyAlarmController(
    private val modifyAlarmUsecase: ModifyAlarmUsecase
) {
    @PatchMapping("/")
    fun modifyAlarm(
        @RequestBody modifyAlarmRequest: ModifyAlarmRequest,
    ): ModifyAlarmResponse {
        return ModifyAlarmResponse(
            modifyAlarmUsecase.modifyAlarm(
                ModifiyAlarmModel(
                    userIdentifier = Identifier("tenm"),
                    enable = modifyAlarmRequest.enable,
                    iteration = modifyAlarmRequest.iteration,
                )
            )
        )
    }
}
