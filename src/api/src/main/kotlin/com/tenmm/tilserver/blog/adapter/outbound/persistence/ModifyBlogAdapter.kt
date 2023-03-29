package com.tenmm.tilserver.blog.adapter.outbound.persistence

import com.tenmm.tilserver.blog.application.inbound.model.ModifyBlogCommand
import com.tenmm.tilserver.blog.application.outbound.ModifyBlogPort
import com.tenmm.tilserver.common.domain.NotFoundException
import com.tenmm.tilserver.outbound.persistence.repository.BlogRepository
import mu.KotlinLogging
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

private val logger = KotlinLogging.logger {}
@Component
class ModifyBlogAdapter(
    private val blogRepository: BlogRepository
) : ModifyBlogPort {
    @Transactional
    override fun modifyIdentifier(command: ModifyBlogCommand): Boolean {
        val entity = blogRepository.findByBlogIdentifier(command.blogIdentifier.value) ?: throw NotFoundException("Not found Blog - ${command.blogIdentifier}")
        return try {
            val modifiedEntity = entity.copy(
                url = command.url.value
            )
            blogRepository.save(modifiedEntity)
            true
        } catch (e: Exception) {
            logger.error(e) { "Blog Modify Fail - $command" }
            false
        }
    }
}
