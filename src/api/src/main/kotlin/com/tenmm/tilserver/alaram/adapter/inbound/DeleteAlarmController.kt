package com.tenmm.tilserver.alaram.adapter.inbound

import com.tenmm.tilserver.alaram.adapter.inbound.model.DeleteAlarmResponse
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/my/settings/notification")
class DeleteAlarmController {
    @DeleteMapping("/{alarmId}")
    fun deleteAlarm(
        @PathVariable alarmId: String,
    ): DeleteAlarmResponse {
        return DeleteAlarmResponse(true)
    }
}
