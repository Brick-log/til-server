package com.tenmm.tilserver.draft.application.inbound

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.draft.domain.Draft
import java.sql.Timestamp

interface SaveDraftUseCase {
    fun saveByUserIdentifier(userIdentifier: Identifier, data: String, updatedAt: Timestamp?): Draft?
}
