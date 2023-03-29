package com.tenmm.tilserver.blog.adapter.outbound.persistence

import com.tenmm.tilserver.blog.application.outbound.DeleteBlogPort
import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.outbound.persistence.repository.BlogRepository
import mu.KotlinLogging
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

private val logger = KotlinLogging.logger {}
@Component
class DeleteBlogAdapter(
    private val blogRepository: BlogRepository
) : DeleteBlogPort {

    @Transactional
    override fun deleteByIdentifier(blogIdentifier: Identifier): Boolean {
        return try {
            blogRepository.deleteByBlogIdentifier(blogIdentifier.value)
            true
        } catch (e: Exception) {
            logger.error(e) { "Blog Delete Fail - $blogIdentifier" }
            false
        }
    }
}
