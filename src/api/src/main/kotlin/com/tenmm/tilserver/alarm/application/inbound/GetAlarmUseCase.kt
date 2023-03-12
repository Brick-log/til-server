package com.tenmm.tilserver.alarm.application.inbound

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.alarm.domain.Alarm

interface GetAlarmUseCase {
    fun getAlarmByUserId(userIdentifier: Identifier): Alarm
}
