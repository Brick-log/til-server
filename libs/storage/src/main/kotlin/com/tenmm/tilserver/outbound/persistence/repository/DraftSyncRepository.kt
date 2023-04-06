package com.tenmm.tilserver.outbound.persistence.repository

import com.tenmm.tilserver.outbound.persistence.entity.DraftSyncEntity
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface DraftSyncRepository : CrudRepository<DraftSyncEntity, String> {
    fun findAll(pageable: Pageable): List<DraftSyncEntity>
    fun findByUserIdentifier(userIdentifier: String): DraftSyncEntity?
    fun deleteByUserIdentifier(userIdentifier: String): Boolean
}
