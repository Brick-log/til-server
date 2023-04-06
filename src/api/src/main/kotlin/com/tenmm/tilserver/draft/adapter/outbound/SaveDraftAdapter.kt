package com.tenmm.tilserver.draft.adapter.outbound

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.common.utils.getNowTimestamp
import com.tenmm.tilserver.draft.application.outbound.SaveDraftPort
import com.tenmm.tilserver.draft.domain.Draft
import com.tenmm.tilserver.outbound.persistence.entity.DraftEntity
import com.tenmm.tilserver.outbound.persistence.repository.DraftRepository
import org.springframework.stereotype.Component

@Component
class SaveDraftAdapter(
    private val draftRepository: DraftRepository,
) : SaveDraftPort {
    override fun saveByUserIdentifier(userIdentifier: Identifier, data: String): Draft? {
        val savedDraft = draftRepository.findByUserIdentifier(userIdentifier.value)

        val newDraft = DraftEntity(
            id = savedDraft?.id ?: 0,
            userIdentifier = userIdentifier.value,
            data = data,
            updatedAt = getNowTimestamp()
        )

        return draftRepository.save(newDraft).toDomain()
    }
}
