package com.tenmm.tilserver.draft.application.outbound

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.draft.domain.Draft
import java.sql.Timestamp

interface SaveDraftPort {
    fun saveByUserIdentifier(userIdentifier: Identifier, data: String, updatedAt: Timestamp?): Draft?
}
