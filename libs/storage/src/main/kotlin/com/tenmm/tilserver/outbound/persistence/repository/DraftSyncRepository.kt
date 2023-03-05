package com.tenmm.tilserver.outbound.persistence.repository

import com.tenmm.tilserver.outbound.persistence.entity.DraftSyncEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface DraftSyncRepository : CrudRepository<DraftSyncEntity, String> {
    fun findByUserIdentifier(userIdentifier: String): DraftSyncEntity?
    fun save(DraftSyncEntity: DraftSyncEntity)
    fun deleteByUserIdentifier(userIdentifier: String)
}
