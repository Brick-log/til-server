package com.tenmm.tilserver.outbound.persistence.repository

import com.tenmm.tilserver.outbound.persistence.entity.DraftSyncEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DraftSyncRepository : JpaRepository<DraftSyncEntity, Long> {
    fun sync(DraftSyncEntity: DraftSyncEntity)
}
