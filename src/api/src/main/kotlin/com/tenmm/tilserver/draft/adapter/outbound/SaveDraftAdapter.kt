package com.tenmm.tilserver.draft.adapter.outbound

import com.tenmm.tilserver.common.utils.getNowTimestamp
import com.tenmm.tilserver.draft.application.outbound.SaveDraftPort
import com.tenmm.tilserver.draft.domain.Draft
import com.tenmm.tilserver.outbound.persistence.entity.DraftEntity
import com.tenmm.tilserver.outbound.persistence.repository.DraftRepository
import java.time.LocalDateTime
import mu.KotlinLogging
import org.springframework.data.redis.core.TimeoutUtils
import org.springframework.stereotype.Component

private val logger = KotlinLogging.logger {}
@Component
class SaveDraftAdapter(
    private val draftRepository: DraftRepository,
) : SaveDraftPort {
    override fun upsert(draft: Draft): Boolean {

        return try {
            val userIdentifier = draft.userIdentifier
            val savedDraft = draftRepository.findByUserIdentifier(userIdentifier.value)
            val newDraft = DraftEntity(
                id = savedDraft?.id ?: 0,
                userIdentifier = userIdentifier.value,
                data = draft.data,
                updatedAt = getNowTimestamp()
            )
            draftRepository.save(newDraft).toDomain()
            return true
        } catch (e: Exception) {
            logger.error(e) { "Draft save Fail - $Draft" }
            false
        }
    }
}
