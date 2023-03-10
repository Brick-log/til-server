package com.tenmm.tilserver.alaram.application.outbound

import com.tenmm.tilserver.alaram.application.inbound.model.ModifyAlarmCommand

interface ModifyAlarmPort {
    fun modifyByUserIdentifier(command: ModifyAlarmCommand): Boolean
}
