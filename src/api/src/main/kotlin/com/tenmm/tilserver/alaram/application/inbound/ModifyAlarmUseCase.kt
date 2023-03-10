package com.tenmm.tilserver.alaram.application.inbound

import com.tenmm.tilserver.alaram.application.inbound.model.ModifyAlarmCommand

interface ModifyAlarmUseCase {
    fun modifyAlarm(alarm: ModifyAlarmCommand): Boolean
}
