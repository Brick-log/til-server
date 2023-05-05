package com.tenmm.tilserver.draft.application.service

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.draft.application.inbound.SyncDraftUseCase
import com.tenmm.tilserver.draft.application.outbound.SaveDraftPort
import com.tenmm.tilserver.draft.application.outbound.SyncDraftPort
import mu.KotlinLogging
import org.springframework.stereotype.Service

private val logger = KotlinLogging.logger {}
private const val GET_DRAFT_COUNT_PER_ONCE = 50

@Service
class SyncDraftService(
    private val saveDraftPort: SaveDraftPort,
    private val syncDraftPort: SyncDraftPort,
) : SyncDraftUseCase {
    override suspend fun syncByUser(userIdentifier: Identifier, data: String) {
        syncDraftPort.save(userIdentifier, data)
    }

    override suspend fun sync() {
        val draftSyncTargets = syncDraftPort.findDraftsWithCount(GET_DRAFT_COUNT_PER_ONCE)
        logger.info("Sync Size: ${draftSyncTargets.size}")
        val saveSucceedDrafts =
            draftSyncTargets.filter {
                draft ->
                saveDraftPort.upsert(draft)
            }.toList()
        logger.info("RDB Sync Success Size: ${saveSucceedDrafts.size}")

        syncDraftPort.delete(saveSucceedDrafts)
    }
}
