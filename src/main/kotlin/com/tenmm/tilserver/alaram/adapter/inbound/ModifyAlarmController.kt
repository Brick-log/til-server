package com.tenmm.tilserver.alaram.adapter.inbound

import com.tenmm.tilserver.alaram.adapter.inbound.model.ModifyAlarmRequest
import com.tenmm.tilserver.alaram.adapter.inbound.model.ModifyAlarmResponse
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/my/settings/notification")
class ModifyAlarmController {
    @PatchMapping("/{alarmId}")
    fun modifyAlarm(
        @PathVariable alarmId: String,
        @RequestBody modifyAlarmRequest: ModifyAlarmRequest,
    ): ModifyAlarmResponse {
        return ModifyAlarmResponse(true)
    }
}
