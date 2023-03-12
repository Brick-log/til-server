package com.tenmm.tilserver.alarm.application.outbound

import com.tenmm.tilserver.alarm.application.inbound.model.ModifyAlarmCommand

interface ModifyAlarmPort {
    fun modifyByUserIdentifier(command: ModifyAlarmCommand): Boolean
}
