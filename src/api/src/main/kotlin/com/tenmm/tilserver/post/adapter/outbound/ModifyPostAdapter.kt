package com.tenmm.tilserver.post.adapter.outbound

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.common.domain.NotFoundException
import com.tenmm.tilserver.outbound.persistence.repository.PostRepository
import com.tenmm.tilserver.post.application.inbound.model.ModifyPostCommand
import com.tenmm.tilserver.post.application.outbound.DeletePostPort
import com.tenmm.tilserver.post.application.outbound.ModifyPostPort
import com.tenmm.tilserver.post.application.outbound.SavePostPort
import com.tenmm.tilserver.post.domain.Post
import java.sql.Timestamp
import java.time.Instant
import mu.KotlinLogging
import org.springframework.stereotype.Component

private val logger = KotlinLogging.logger {}

@Component
class ModifyPostAdapter(
    private val postRepository: PostRepository,
) : SavePostPort, ModifyPostPort, DeletePostPort {
    override fun deleteByIdentifier(identifier: Identifier): Boolean {
        return try {
            postRepository.deleteByIdentifier(identifier = identifier.value)
        } catch (e: Exception) {
            logger.error(e) { "Post Delete Fail - $identifier" }
            false
        }
    }

    override fun modifyByIdentifier(command: ModifyPostCommand): Boolean {
        val postEntity = postRepository.findByIdentifier(command.identifier.value)
            ?: throw NotFoundException("Not found Post - ${command.identifier}")

        val modifiedEntity = postEntity.copy(
            title = command.title,
            description = command.summary,
            createdAt = Timestamp.from(Instant.ofEpochMilli(command.createdAt))
        )

        return try {
            postRepository.save(modifiedEntity)
            true
        } catch (e: Exception) {
            logger.error(e) { "Post save Fail - $command" }
            false
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
}
