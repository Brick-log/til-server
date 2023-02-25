package com.tenmm.tilserver.draft.application.service

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.draft.application.inbound.GetDraftUseCase
import com.tenmm.tilserver.draft.domain.Draft
import com.tenmm.tilserver.draft.adapter.outbound.model.DraftEntity
import com.tenmm.tilserver.draft.adapter.outbound.DraftRepository
import org.springframework.stereotype.Service

@Service
class GetDraftService(
    private val draftRepository: DraftRepository
) : GetDraftUseCase {
    override fun getByUserIdentifier(userIdentifier: Identifier): Draft {
        val draftEntity: DraftEntity = draftRepository.findByUserIdentifier(userIdentifier.value)
        TODO("Not yet implemented")
    }
}
