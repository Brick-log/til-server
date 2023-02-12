package com.tenmm.tilserver.alaram.application.service

import com.tenmm.tilserver.alaram.application.inbound.model.SaveAlarmModel
import com.tenmm.tilserver.alaram.application.inbound.SaveAlarmUsecase
import org.springframework.stereotype.Service

@Service
class SaveAlarmService: SaveAlarmUsecase {
    override fun saveAlarm(saveAlarmModel: SaveAlarmModel): String {
        TODO("Not yet implemented")
        return "0 0 0 * * *"
    }
}
