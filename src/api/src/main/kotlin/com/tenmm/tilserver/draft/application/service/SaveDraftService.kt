package com.tenmm.tilserver.draft.application.service

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.draft.domain.Draft
import com.tenmm.tilserver.draft.application.inbound.SaveDraftUseCase
import com.tenmm.tilserver.draft.application.outbound.SaveDraftPort
import com.tenmm.tilserver.draft.application.outbound.SyncDraftPort
import org.springframework.stereotype.Service

@Service
class SaveDraftService(
    private val saveDraftPort: SaveDraftPort,
    private val syncDraftPort: SyncDraftPort,
) : SaveDraftUseCase {
    override fun saveByUserIdentifier(userIdentifier: Identifier, data: String): Draft? {
        /**
         * redis 내용 있으면 삭제
         */
        syncDraftPort.findById(userIdentifier)?.let {
            syncDraftPort.deleteById(userIdentifier)
        }
        /**
         * RDB에 저장
         */
        return saveDraftPort.saveByUserIdentifier(userIdentifier, data)
    }
}
