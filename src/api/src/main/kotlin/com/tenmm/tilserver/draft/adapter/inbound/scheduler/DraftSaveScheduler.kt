package com.tenmm.tilserver.draft.adapter.inbound.scheduler

import com.tenmm.tilserver.draft.application.inbound.SyncDraftUseCase
import java.time.LocalDateTime
import java.util.concurrent.CompletableFuture
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import mu.KotlinLogging
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

private val logger = KotlinLogging.logger {}
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
        logger.info("Draft Sync Start -> ${LocalDateTime.now()}")
        CoroutineScope(Dispatchers.Default).launch {
            syncDraftUseCase.sync()
        }

    }
}
