package com.tenmm.tilserver.draft.application.service

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.draft.application.inbound.SyncDraftUseCase
import com.tenmm.tilserver.draft.adapter.outbound.model.DraftSyncEntity
import com.tenmm.tilserver.draft.adapter.outbound.DraftSyncRepository
import org.springframework.stereotype.Service
import java.sql.Timestamp

@Service
class SyncDraftService(
    private val draftSyncRepository: DraftSyncRepository
) : SyncDraftUseCase {
    override fun sync(draftIdentifier: Identifier, data: String) {
        draftSyncRepository.save(
            DraftSyncEntity(
                UserIdentifier = draftIdentifier,
                Data = data,
                UpdatedAt = Timestamp(System.currentTimeMillis())  // TODO: DB 에서 처리하도록 변경
            )
        )
        TODO("Not yet implemented")
    }
}
