package com.tenmm.tilserver.alarm.application.inbound

import com.tenmm.tilserver.alarm.domain.Alarm
import com.tenmm.tilserver.common.domain.Identifier

interface GetAlarmUseCase {
    fun getAlarmByUserId(userIdentifier: Identifier): Alarm
}
