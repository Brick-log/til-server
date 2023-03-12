package com.tenmm.tilserver.draft.application.service

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.draft.application.inbound.SyncDraftUseCase
import org.springframework.stereotype.Service
import com.tenmm.tilserver.draft.application.outbound.SyncDraftPort
import com.tenmm.tilserver.draft.domain.Draft

@Service
class SyncDraftService(
    private val syncDraftPort: SyncDraftPort,
) : SyncDraftUseCase {
    override fun save(draftIdentifier: Identifier, data: String) {
        syncDraftPort.save(draftIdentifier, data)
    }

    override fun findById(userIdentifier: Identifier): Draft? {
        return syncDraftPort.findById(userIdentifier)
    }

    override fun deleteById(userIdentifier: Identifier) {
        syncDraftPort.deleteById(userIdentifier)
    }

    override fun  findAll(): List<Draft> {
        return syncDraftPort.findAll()
    }
}
