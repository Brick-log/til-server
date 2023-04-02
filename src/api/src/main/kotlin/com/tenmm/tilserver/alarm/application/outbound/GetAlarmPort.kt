package com.tenmm.tilserver.alarm.application.outbound

import com.tenmm.tilserver.alarm.domain.Alarm
import com.tenmm.tilserver.common.domain.Identifier

interface GetAlarmPort {
    fun findByUserIdentifier(userIdentifier: Identifier): Alarm?
}
