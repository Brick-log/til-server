package com.tenmm.tilserver.alaram.application.outbound

import com.tenmm.tilserver.alaram.domain.Alarm
import com.tenmm.tilserver.common.domain.Identifier

interface GetAlarmPort {
    fun findByUserIdentifier(userIdentifier: Identifier): Alarm?
}
