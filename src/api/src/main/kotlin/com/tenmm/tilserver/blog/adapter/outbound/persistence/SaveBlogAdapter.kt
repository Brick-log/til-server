package com.tenmm.tilserver.blog.adapter.outbound.persistence

import com.tenmm.tilserver.blog.adapter.outbound.persistence.converters.toEntity
import com.tenmm.tilserver.blog.application.inbound.model.SaveBlogCommand
import com.tenmm.tilserver.blog.application.outbound.SaveBlogPort
import com.tenmm.tilserver.blog.domain.Blog
import com.tenmm.tilserver.outbound.persistence.repository.BlogRepository
import mu.KotlinLogging
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

private val logger = KotlinLogging.logger {}
@Component
class SaveBlogAdapter(
    private val blogRepository: BlogRepository
) : SaveBlogPort {
    @Transactional
    override fun save(command: SaveBlogCommand): Boolean {
        val model = Blog(
            identifier = command.blogIdentifier,
            userIdentifier = command.userIdentifier,
            url = command.url
        )
        val entity = model.toEntity()
        return try {
            blogRepository.save(entity)
            true
        } catch (e: Exception) {
            logger.error(e) { "Blog Save Fail - $model" }
            false
        }
    }
}
