package com.tenmm.tilserver.draft.adapter.outbound

import org.springframework.data.jpa.repository.JpaRepository
import com.tenmm.tilserver.draft.adapter.outbound.model.DraftEntity
import org.springframework.stereotype.Repository

@Repository
interface DraftRepository : JpaRepository<DraftEntity, Long> {
    fun findByUserIdentifier(userIdentifier: String): DraftEntity
    fun save(draftEntity: DraftEntity): DraftEntity
}
