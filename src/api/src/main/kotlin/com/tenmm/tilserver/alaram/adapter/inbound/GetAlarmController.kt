package com.tenmm.tilserver.alaram.adapter.inbound

import com.tenmm.tilserver.alaram.application.inbound.GetAlarmUsecase
import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.alaram.domain.Alarm
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/v1/my/notification")
class GetAlarmController(
    private val getAlarmUsecase: GetAlarmUsecase
) {
    @GetMapping()
    fun getAlarm(): Alarm {
        // TODO: userIdentifier 토큰에서 가져오도록 수정
        return getAlarmUsecase.getAlarmByUserId(Identifier(UUID.randomUUID().toString()))
    }
}
