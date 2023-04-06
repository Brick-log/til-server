package com.tenmm.tilserver.draft.adapter.outbound

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.common.utils.getNowTimestamp
import com.tenmm.tilserver.draft.application.outbound.SyncDraftPort
import com.tenmm.tilserver.draft.domain.Draft
import com.tenmm.tilserver.outbound.persistence.entity.DraftSyncEntity
import com.tenmm.tilserver.outbound.persistence.repository.DraftSyncRepository
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Component

@Component
class SyncDraftAdapter(
    private val draftSyncRepository: DraftSyncRepository,
) : SyncDraftPort {
    override fun save(userIdentifier: Identifier, data: String) {
        draftSyncRepository.save(
            DraftSyncEntity(
                userIdentifier = userIdentifier.value,
                data = data,
                updatedAt = getNowTimestamp()
            )
        )
    }

    override fun findByUserIdentifier(userIdentifier: Identifier): Draft? {
        val draftSyncEntity = draftSyncRepository.findByUserIdentifier(userIdentifier.value)
        return draftSyncEntity?.toDomain()
    }

    override fun findDraftsWithCount(size: Int): List<Draft> {
        val pageable = PageRequest.ofSize(size)
        return draftSyncRepository.findAll(pageable).map { it.toDomain() }
    }

    override fun deleteById(userIdentifier: Identifier): Boolean {
        return draftSyncRepository.deleteByUserIdentifier(userIdentifier.value)
    }
}
