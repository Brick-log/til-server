package com.tenmm.tilserver.draft.application.service

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.draft.application.inbound.SyncDraftUseCase
import com.tenmm.tilserver.draft.application.outbound.SyncDraftPort
import mu.KotlinLogging
import org.springframework.stereotype.Service

private val logger = KotlinLogging.logger {}

@Service
class SyncDraftService(
    private val syncDraftPort: SyncDraftPort,
) : SyncDraftUseCase {
    override suspend fun syncByUser(userIdentifier: Identifier, data: String): Boolean {
        return syncDraftPort.save(userIdentifier, data)
    }
}
