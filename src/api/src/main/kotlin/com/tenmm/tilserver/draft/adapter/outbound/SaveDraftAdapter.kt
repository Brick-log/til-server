package com.tenmm.tilserver.draft.adapter.outbound

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.draft.application.outbound.SaveDraftPort
import com.tenmm.tilserver.outbound.persistence.repository.DraftRepository
import com.tenmm.tilserver.outbound.persistence.entity.DraftEntity
import java.time.LocalDateTime
import org.springframework.stereotype.Component
import com.tenmm.tilserver.draft.domain.Draft
import java.sql.Timestamp

@Component
class SaveDraftAdapter(
    private val draftRepository: DraftRepository
) : SaveDraftPort {
    override fun saveByUserIdentifier(userIdentifier: Identifier, data: String): Draft? {
        val savedDraft: DraftEntity? = draftRepository.findByUserIdentifier(userIdentifier.value)
        val newDraft: DraftEntity = DraftEntity(
            id = savedDraft?.id ?: 0,
            userIdentifier = userIdentifier.value,
            data = data,
            updatedAt = LocalDateTime.now()
        )
        draftRepository.save(newDraft)
        return Draft(
            userIdentifier = Identifier(newDraft.userIdentifier),
            data = data,
            updatedAt = Timestamp.valueOf(newDraft.updatedAt)
        )
    }
}
