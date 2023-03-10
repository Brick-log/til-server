package com.tenmm.tilserver.post.adapter.outbound

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.outbound.persistence.entity.RecommendPostEntity
import com.tenmm.tilserver.outbound.persistence.repository.RecommendPostRepository
import com.tenmm.tilserver.post.application.outbound.AddRecommendedPostPort
import com.tenmm.tilserver.post.application.outbound.GetRecommendedPostPort
import java.sql.Timestamp
import java.time.Instant
import mu.KotlinLogging
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Component

private val logger = KotlinLogging.logger {}

@Component
class RecommendedPostAdapter(
    private val recommendPostRepository: RecommendPostRepository,
) : AddRecommendedPostPort, GetRecommendedPostPort {
    override fun addByPostIdentifier(
        categoryIdentifier: Identifier,
        postIdentifier: Identifier,
    ): Boolean {
        val entity = RecommendPostEntity(
            categoryIdentifier = categoryIdentifier.value,
            postIdentifier = postIdentifier.value,
            createdAt = Timestamp.from(Instant.now())
        )
        return try {
            recommendPostRepository.save(entity)
            true
        } catch (e: Exception) {
            logger.error(e) { "RecommendedPost Add Fail - $postIdentifier" }
            false
        }
    }

    override fun getByCategoryIdentifier(categoryIdentifier: Identifier): List<Identifier> {
        return recommendPostRepository.findByCategoryIdentifierOrderByCreatedAtDesc(
            categoryIdentifier.value, PageRequest.ofSize(3)
        ).map { Identifier(it.postIdentifier) }
    }
}
