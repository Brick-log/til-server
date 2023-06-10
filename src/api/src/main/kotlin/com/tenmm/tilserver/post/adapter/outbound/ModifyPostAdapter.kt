package com.tenmm.tilserver.post.adapter.outbound

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.common.domain.OperationResult
import com.tenmm.tilserver.outbound.persistence.repository.PostRepository
import com.tenmm.tilserver.post.application.outbound.DeletePostPort
import com.tenmm.tilserver.post.application.outbound.ModifyPostPort
import com.tenmm.tilserver.post.application.outbound.SavePostPort
import com.tenmm.tilserver.post.domain.Post
import java.sql.Timestamp
import java.time.Instant
import mu.KotlinLogging
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

private val logger = KotlinLogging.logger {}

@Component
class ModifyPostAdapter(
    private val postRepository: PostRepository,
) : SavePostPort, ModifyPostPort, DeletePostPort {
    @Transactional
    override fun deleteByIdentifier(identifier: Identifier): Boolean {
        return try {
            postRepository.deleteByIdentifier(identifier = identifier.value) == 1
        } catch (e: Exception) {
            logger.error(e) { "Post Delete Fail - $identifier" }
            false
        }
    }

    override fun updateTitle(postIdentifier: Identifier, title: String): OperationResult {
        try {
            val postEntity = postRepository.findByIdentifier(postIdentifier.value)
                ?: return OperationResult.fail()

            val modifiedEntity = postEntity.copy(
                title = title
            )

            postRepository.save(modifiedEntity)
            return OperationResult.success()
        } catch (e: Exception) {
            logger.error(e) { "Post save Fail" }
            return OperationResult.fail()
        }
    }

    override fun updateSummary(postIdentifier: Identifier, summary: String): OperationResult {
        try {
            val postEntity = postRepository.findByIdentifier(postIdentifier.value)
                ?: return OperationResult.fail()

            val modifiedEntity = postEntity.copy(
                description = summary
            )

            postRepository.save(modifiedEntity)
            return OperationResult.success()
        } catch (e: Exception) {
            logger.error(e) { "Post save Fail" }
            return OperationResult.fail()
        }
    }

    override fun updateCreatedAt(postIdentifier: Identifier, createdAt: Long): OperationResult {
        try {
            val postEntity = postRepository.findByIdentifier(postIdentifier.value)
                ?: return OperationResult.fail()

            val modifiedEntity = postEntity.copy(
                createdAt = Timestamp.from(Instant.ofEpochSecond(createdAt))
            )

            postRepository.save(modifiedEntity)
            return OperationResult.success()
        } catch (e: Exception) {
            logger.error(e) { "Post save Fail" }
            return OperationResult.fail()
        }
    }

    override fun save(post: Post): Boolean {
        return try {
            postRepository.save(post.toEntity())
            true
        } catch (e: Exception) {
            logger.error(e) { "Post save Fail - $post" }
            false
        }
    }

    override fun increasePostHitCount(postIdentifier: Identifier) {
        val postEntity = postRepository.findByIdentifier(identifier = postIdentifier.value) ?: return

        postRepository.save(postEntity.copy(hitCount = postEntity.hitCount + 1))
    }
}
