package com.tenmm.tilserver.draft.application.outbound

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.draft.domain.Draft

interface GetDraftPort {
    fun findByUserIdentifier(userIdentifier: Identifier): Draft?
}
