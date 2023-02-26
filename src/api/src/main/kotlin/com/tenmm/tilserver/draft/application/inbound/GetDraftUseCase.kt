package com.tenmm.tilserver.draft.application.inbound

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.draft.domain.Draft

interface GetDraftUseCase {
    fun getByUserIdentifier(userIdentifier: Identifier): Draft?
}
