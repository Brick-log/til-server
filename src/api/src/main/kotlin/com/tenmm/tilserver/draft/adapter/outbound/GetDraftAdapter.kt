package com.tenmm.tilserver.draft.adapter.outbound

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.draft.domain.Draft
import com.tenmm.tilserver.draft.application.outbound.GetDraftPort
import com.tenmm.tilserver.outbound.persistence.entity.DraftEntity
import com.tenmm.tilserver.outbound.persistence.repository.DraftRepository
import java.sql.Timestamp
import org.springframework.stereotype.Component

@Component
class GetDraftAdapter(
    private val draftRepository: DraftRepository,
) : GetDraftPort {
    override fun findByUserIdentifier(userIdentifier: Identifier): Draft? {
        val draftEntity: DraftEntity? = draftRepository.findByUserIdentifier(userIdentifier.value)
        return draftEntity?.let {
            Draft(
                userIdentifier = Identifier(it.userIdentifier),
                data = it.data,
                updatedAt = Timestamp.valueOf(it.updatedAt)
            )
        } ?: null
    }
}
