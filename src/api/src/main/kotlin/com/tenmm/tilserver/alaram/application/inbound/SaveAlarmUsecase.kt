package com.tenmm.tilserver.alaram.application.inbound

import com.tenmm.tilserver.alaram.application.inbound.model.SaveAlarmModel

interface SaveAlarmUsecase {
    fun saveAlarm(saveAlarmModel: SaveAlarmModel): String
}