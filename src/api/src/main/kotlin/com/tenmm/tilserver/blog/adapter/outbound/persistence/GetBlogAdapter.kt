package com.tenmm.tilserver.blog.adapter.outbound.persistence

import com.tenmm.tilserver.blog.adapter.outbound.persistence.converters.toModel
import com.tenmm.tilserver.blog.application.outbound.GetBlogPort
import com.tenmm.tilserver.blog.domain.Blog
import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.common.domain.NotFoundException
import com.tenmm.tilserver.outbound.persistence.repository.BlogRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class GetBlogAdapter(
    private val blogRepository: BlogRepository
) : GetBlogPort {
    override fun getByBlogIdentifier(blogIdentifier: Identifier): Blog {
        val entity = blogRepository.findByBlogIdentifier(blogIdentifier.value) ?: throw NotFoundException("Not found Blog - $blogIdentifier")
        return entity.toModel()
    }

    @Transactional(readOnly = true)
    override fun getByUserIdentifier(userIdentifier: Identifier): List<Blog> {
        return blogRepository.findAllByUserIdentifier(userIdentifier.value).map { it.toModel() }
    }
}
