package com.tenmm.tilserver.draft.adapter.persistence.outbound

import com.tenmm.tilserver.draft.adapter.outbound.model.DraftSyncEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DraftSyncRepository : JpaRepository<DraftSyncEntity, String> {
    fun save(draftSyncEntity: DraftSyncEntity): DraftSyncEntity
}
