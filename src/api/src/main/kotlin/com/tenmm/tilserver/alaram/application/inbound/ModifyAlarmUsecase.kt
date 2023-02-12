package com.tenmm.tilserver.alaram.application.inbound

import com.tenmm.tilserver.alaram.application.inbound.model.ModifiyAlarmModel

interface ModifyAlarmUsecase {
    fun modifyAlarm(alarm: ModifiyAlarmModel): Boolean
}