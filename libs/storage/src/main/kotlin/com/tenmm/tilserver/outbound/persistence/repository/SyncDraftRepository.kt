package com.tenmm.tilserver.outbound.persistence.repository

import com.tenmm.tilserver.outbound.persistence.entity.SyncDraftEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SyncDraftRepository : JpaRepository<SyncDraftEntity, Long> {
    fun findByUserIdentifier(userIdentifier: String): SyncDraftEntity?
}
