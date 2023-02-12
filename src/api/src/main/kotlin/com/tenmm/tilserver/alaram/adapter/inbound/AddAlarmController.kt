package com.tenmm.tilserver.alaram.adapter.inbound

import com.tenmm.tilserver.alaram.adapter.inbound.model.AddAlarmRequest
import com.tenmm.tilserver.alaram.adapter.inbound.model.AddAlarmResponse
import com.tenmm.tilserver.alaram.application.inbound.SaveAlarmUsecase
import com.tenmm.tilserver.alaram.application.inbound.model.SaveAlarmModel
import com.tenmm.tilserver.common.domain.Identifier
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/my/settings/notification")
class AddAlarmController(
    private val saveAlarmUsecase: SaveAlarmUsecase
) {
    @PostMapping
    fun addAlarm(
        @RequestBody addAlarmRequest: AddAlarmRequest,
    ): AddAlarmResponse {
        return AddAlarmResponse(
            saveAlarmUsecase.saveAlarm(
                SaveAlarmModel(
                    userIdentifier = Identifier("tenm"),
                    enable = addAlarmRequest.enable,
                    iteration = addAlarmRequest.iteration,
                )
            )
        )
    }
}
