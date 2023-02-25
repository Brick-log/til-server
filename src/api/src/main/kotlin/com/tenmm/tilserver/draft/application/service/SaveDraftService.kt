package com.tenmm.tilserver.draft.application.service

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.draft.application.inbound.SaveDraftUseCase
import com.tenmm.tilserver.draft.adapter.outbound.model.DraftEntity
import com.tenmm.tilserver.draft.adapter.outbound.DraftRepository
import org.springframework.stereotype.Service
import java.sql.Timestamp

@Service
class SaveDraftService(
    private val draftRepository: DraftRepository
) : SaveDraftUseCase {
    override fun save(userIdentifier: Identifier, data: String) {
        draftRepository.save(
            DraftEntity(
                UserIdentifier = userIdentifier,
                Data = data,
                UpdatedAt = Timestamp(System.currentTimeMillis()) // TODO: DB 에서 처리하도록 변경
            )
        )
        TODO("Not yet implemented")
    }
}
