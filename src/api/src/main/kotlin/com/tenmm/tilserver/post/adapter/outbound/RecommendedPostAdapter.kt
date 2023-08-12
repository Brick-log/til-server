package com.tenmm.tilserver.post.adapter.outbound

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.common.domain.toIdentifier
import com.tenmm.tilserver.outbound.persistence.entity.RecommendPostEntity
import com.tenmm.tilserver.outbound.persistence.repository.RecommendPostRepository
import com.tenmm.tilserver.post.application.outbound.AddRecommendedPostPort
import com.tenmm.tilserver.post.application.outbound.GetRecommendedPostPort
import mu.KotlinLogging
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Component

private val logger = KotlinLogging.logger {}

@Component
class RecommendedPostAdapter(
    private val recommendPostRepository: RecommendPostRepository,
) : AddRecommendedPostPort, GetRecommendedPostPort {
    override fun getRandom(size: Int): List<Identifier> {
        return recommendPostRepository.findByRandom(size).map { it.postIdentifier.toIdentifier() }
    }

    override fun addByPostIdentifier(
        categoryIdentifier: String,
        postIdentifier: Identifier,
    ): Boolean {
        val entity = RecommendPostEntity(
            categoryIdentifier = categoryIdentifier,
            postIdentifier = postIdentifier.value
        )
        return try {
            recommendPostRepository.save(entity)
            true
        } catch (e: Exception) {
            logger.error(e) { "RecommendedPost Add Fail - $postIdentifier" }
            false
        }
    }

    override fun getByCategoryIdentifier(categoryIdentifier: String): List<Identifier> {
        return recommendPostRepository.findByCategoryIdentifierOrderByCreatedAtDesc(
            categoryIdentifier, PageRequest.ofSize(3)
        ).map { Identifier(it.postIdentifier) }
    }
}
