package com.tenmm.tilserver.alaram.adapter.inbound

import com.tenmm.tilserver.alaram.adapter.inbound.model.AddAlarmRequest
import com.tenmm.tilserver.alaram.adapter.inbound.model.AddAlarmResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/my/settings/notification")
class AddAlarmController {
    @PostMapping
    fun addAlarm(
        @RequestBody addAlarmRequest: AddAlarmRequest,
    ): AddAlarmResponse {
        return AddAlarmResponse(
            alarmId = addAlarmRequest.name
        )
    }
}
