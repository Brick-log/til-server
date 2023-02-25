package com.tenmm.tilserver.draft.application.service

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.draft.application.inbound.GetDraftUseCase
import com.tenmm.tilserver.draft.domain.Draft
import org.springframework.stereotype.Service

@Service
class GetDraftService(
    private val getDraftPort: GetDraftPort,
) : GetDraftUseCase {
    override fun getByUserIdentifier(userIdentifier: Identifier): Draft {
        return getDraftPort.getDraft(userIdentifier)
        TODO("Not yet implemented")
    }
}
