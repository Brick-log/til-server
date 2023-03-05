package com.tenmm.tilserver.draft.adapter.inbound.scheduler

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.draft.application.inbound.SaveDraftUseCase
import com.tenmm.tilserver.draft.application.inbound.SyncDraftUseCase
import org.springframework.stereotype.Service
import com.tenmm.tilserver.draft.domain.Draft

@Service
class DraftSaveScheduler(
    private val saveDraftUseCase: SaveDraftUseCase,
    private val syncDraftUseCase: SyncDraftUseCase,
) {
    /**
     * TODO
     * Redis에 저장되어 있는 Key 값에 대한 일괄적인 저장
     * Redis -> RDB
     */
    // @Scheduled
    fun saveAll() {
        val draft: Draft? = syncDraftUseCase.getByUserIdentifier(Identifier.generate())
        draft?.let {
            saveDraftUseCase.saveByUserIdentifier(
                userIdentifier = draft.userIdentifier,
                data = draft.data
            )
            syncDraftUseCase.deleteByUserIdentifier(draft.userIdentifier)
        }
    }
}
