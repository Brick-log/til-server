package com.tenmm.tilserver.draft.adapter.outbound

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.draft.application.outbound.SaveDraftPort
import com.tenmm.tilserver.outbound.persistence.repository.DraftRepository
import com.tenmm.tilserver.outbound.persistence.entity.DraftEntity
import java.time.LocalDateTime

class SaveDraftAdapter(
    private val draftRepository: DraftRepository
) : SaveDraftPort {
    override fun saveDraft(userIdentifier: Identifier, data: String) {
        draftRepository.save(
            DraftEntity(
                userIdentifier = userIdentifier.value,
                data = data,
                updatedAt = LocalDateTime.now()
            )
        )
    }
}
