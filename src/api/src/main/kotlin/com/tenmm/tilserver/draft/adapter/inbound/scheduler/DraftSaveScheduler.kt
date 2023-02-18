package com.tenmm.tilserver.draft.adapter.inbound.scheduler

import com.tenmm.tilserver.draft.application.inbound.SaveDraftUseCase
import org.springframework.stereotype.Service

@Service
class DraftSaveScheduler(
    private val saveDraftUseCase: SaveDraftUseCase
) {
    /**
     * TODO
     * Redis에 저장되어 있는 Key 값에 대한 일괄적인 저장
     * Redis -> RDB
     */
    // @Scheduled
    fun saveAll() {
        saveDraftUseCase.saveAll()
    }
}
