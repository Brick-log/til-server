package com.tenmm.tilserver.alaram.application.inbound

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.alaram.domain.Alarm

interface GetAlarmUseCase {
    fun getAlarmByUserId(userIdentifier: Identifier): Alarm
}
