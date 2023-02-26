package com.tenmm.tilserver.outbound.persistence.repository

import com.tenmm.tilserver.outbound.persistence.entity.DraftEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DraftRepository : JpaRepository<DraftEntity, Long> {
    fun findByUserIdentifier(userIdentifier: String): DraftEntity?
    fun sync(draftEntity: DraftEntity)
}
