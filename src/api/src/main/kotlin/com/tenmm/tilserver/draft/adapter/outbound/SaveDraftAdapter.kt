package com.tenmm.tilserver.draft.adapter.outbound

import com.tenmm.tilserver.draft.application.outbound.SaveDraftPort
import com.tenmm.tilserver.draft.adapter.outbound.persistence.DraftRepository
import com.tenmm.tilserver.draft.adapter.outbound.persistence.model.DraftEntity
import java.sql.Timestamp

class SaveDraftAdapter (
    private val draftRepository: DraftRepository
) :SaveDraftPort {
    fun saveDraft(userIdentifier: Identifier, data: String) {
        draftRepository.saveDraft(
            DraftEntity(
                userIdentifier = userIdentifier,
                data = data,
                updatedAt = Timestamp(System.currentTimeMillis())
            )
        )
    }
}