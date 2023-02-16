package com.tenmm.tilserver.alaram.application.service

import com.tenmm.tilserver.alaram.application.inbound.model.ModifiyAlarmModel
import com.tenmm.tilserver.alaram.application.inbound.ModifyAlarmUsecase
import org.springframework.stereotype.Service

@Service
class ModifyAlarmService : ModifyAlarmUsecase {
    override fun modifyAlarm(alarm: ModifiyAlarmModel): Boolean {
        // TODO("Not yet implemented")
        return true
    }
}
