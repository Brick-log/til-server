package com.tenmm.tilserver.alaram.adapter.inbound

import com.tenmm.tilserver.alaram.application.inbound.GetAlarmUsecase
import com.tenmm.tilserver.alaram.domain.Alarm
import com.tenmm.tilserver.common.domain.Identifier
import io.swagger.v3.oas.annotations.tags.Tag
import java.util.UUID
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@Tag(name = "alarm")
@RequestMapping("/v1/my/notification")
class GetAlarmController(
    private val getAlarmUsecase: GetAlarmUsecase,
) {
    @GetMapping
    fun getAlarm(): Alarm {
        // TODO: userIdentifier 토큰에서 가져오도록 수정
        return getAlarmUsecase.getAlarmByUserId(Identifier(UUID.randomUUID().toString()))
    }
}
