package com.tenmm.tilserver.alaram.application.service

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.alaram.application.inbound.GetAlarmUseCase
import com.tenmm.tilserver.alaram.domain.Alarm
import org.springframework.stereotype.Service

@Service
class GetAlarmService : GetAlarmUseCase {
    override fun getAlarmByUserId(userIdentifier: Identifier): Alarm {
        // TODO("Not yet implemented")
        return Alarm(
            userIdentifier = userIdentifier,
            enable = true,
            iteration = "0 0 0 * * *"
        )
    }
}
