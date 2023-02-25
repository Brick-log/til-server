package com.tenmm.tilserver.draft.adapter.outbound

import com.tenmm.tilserver.draft.application.outbound.GetDraftPort
import com.tenmm.tilserver.draft.adapter.outbound.persistence.DraftRepository
import com.tenmm.tilserver.draft.adapter.outbound.persistence.model.DraftEntity

class GetDraftAdapter(
    private val draftRepository: DraftRepository
) : GetDraftPort {
    fun getDraft(userIdentifier: Identifier): DraftEntity {
        return draftRepository.getDraft(userIdentifier)
    }
}