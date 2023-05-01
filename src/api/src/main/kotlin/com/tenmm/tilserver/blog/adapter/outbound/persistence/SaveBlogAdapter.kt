package com.tenmm.tilserver.blog.adapter.outbound.persistence

import com.tenmm.tilserver.blog.adapter.outbound.persistence.converters.toEntity
import com.tenmm.tilserver.blog.application.outbound.SaveBlogPort
import com.tenmm.tilserver.blog.domain.Blog
import com.tenmm.tilserver.outbound.persistence.repository.BlogRepository
import mu.KotlinLogging
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

private val logger = KotlinLogging.logger {}

@Component
class SaveBlogAdapter(
    private val blogRepository: BlogRepository,
) : SaveBlogPort {

    @Transactional
    override fun saveAll(blogs: List<Blog>): Boolean {
        val entities = blogs.map { it.toEntity() }
        return try {
            blogRepository.saveAll(entities)
            true
        } catch (e: Exception) {
            logger.error(e) { "Blog Save Fail - $entities" }
            false
        }
    }
}
