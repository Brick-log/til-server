package com.tenmm.tilserver.security.adapter.inbound.scheduler

import com.tenmm.tilserver.security.application.inbound.DeleteTokenUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class DeleteExpiredTokenScheduler(
    private val deleteTokenUseCase: DeleteTokenUseCase
) {
    /**
     * 1시간에 한번 씩 비우기
     */
    @Scheduled(fixedRate = 3600000L)
    fun deleteAllExpiredTokens() {
        CoroutineScope(Dispatchers.Default).launch {
            deleteTokenUseCase.deleteAllExpiredTokens()
        }
    }
}
