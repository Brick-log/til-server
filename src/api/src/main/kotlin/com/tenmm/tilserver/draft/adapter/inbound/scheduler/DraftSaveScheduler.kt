package com.tenmm.tilserver.draft.adapter.inbound.scheduler

import com.tenmm.tilserver.draft.application.inbound.SyncDraftUseCase
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class DraftSaveScheduler(
    private val syncDraftUseCase: SyncDraftUseCase,
) {
    /**
     * 10분마다 Redis에 저장되어 있는 모든 Key 값에 대한 일괄적인 저장
     */
    @Scheduled(fixedDelay = 60000)
    fun saveAll() {
        /**
         * Redis에 저장되어 있는 모든 Key 값에 대한 일괄적인 저장
         * Redis에 저장되어 있는 Key 값은 삭제
         * Redis -> RDB
         */
        syncDraftUseCase.sync()
    }
}
