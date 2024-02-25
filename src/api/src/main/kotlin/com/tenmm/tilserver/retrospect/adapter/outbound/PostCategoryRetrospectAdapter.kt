package com.tenmm.tilserver.retrospect.adapter.outbound

import com.tenmm.tilserver.retrospect.application.outbound.PostCategoryRetrospectPort
import com.tenmm.tilserver.outbound.persistence.repository.RecommendRetrospectRepository
import com.tenmm.tilserver.outbound.persistence.entity.RecommendRetrospectEntity
import org.springframework.stereotype.Component

import com.tenmm.tilserver.common.domain.Identifier

@Component
class PostCategoryRetrospectAdapter(
    private val recommendRetrospectRepository: RecommendRetrospectRepository,
) : PostCategoryRetrospectPort {
    override fun addByRetrospectIdentifier(
        categoryIdentifier: String,
        retrospectIdentifier: Identifier,
    ): Boolean {
        val entity = RecommendRetrospectEntity(
            categoryIdentifier = categoryIdentifier,
            retrospectIdentifier = retrospectIdentifier.value
        )
        return try {
            recommendRetrospectRepository.save(entity)
            true
        } catch (e: Exception) {
            false
        }
    }
}
