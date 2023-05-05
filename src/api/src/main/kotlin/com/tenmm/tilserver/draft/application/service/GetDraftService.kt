package com.tenmm.tilserver.draft.application.service

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.draft.application.inbound.GetDraftUseCase
import com.tenmm.tilserver.draft.application.outbound.GetDraftPort
import com.tenmm.tilserver.draft.application.outbound.SyncDraftPort
import com.tenmm.tilserver.draft.domain.Draft
import org.springframework.stereotype.Service

@Service
class GetDraftService(
    private val getDraftPort: GetDraftPort,
    private val syncDraftPort: SyncDraftPort,
) : GetDraftUseCase {
    override suspend fun getByUserIdentifier(userIdentifier: Identifier): Draft? {
        return syncDraftPort.findByUserIdentifier(userIdentifier) ?: getDraftPort.findByUserIdentifier(userIdentifier)
    }
}
