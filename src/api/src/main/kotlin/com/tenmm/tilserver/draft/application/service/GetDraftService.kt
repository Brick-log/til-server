package com.tenmm.tilserver.draft.application.service

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.draft.application.inbound.GetDraftUseCase
import com.tenmm.tilserver.draft.domain.Draft
import org.springframework.stereotype.Service

@Service
class GetDraftService : GetDraftUseCase {
    override fun getByUserIdentifier(userIdentifier: Identifier): Draft {
        TODO("Not yet implemented")
    }
}
