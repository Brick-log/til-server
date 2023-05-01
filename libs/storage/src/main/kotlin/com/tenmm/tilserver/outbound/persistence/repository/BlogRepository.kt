package com.tenmm.tilserver.outbound.persistence.repository

import com.tenmm.tilserver.outbound.persistence.entity.BlogEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BlogRepository : JpaRepository<BlogEntity, Long> {
    fun findByBlogIdentifier(blogIdentifier: String): BlogEntity?
    fun findAllByUserIdentifier(userIdentifier: String): List<BlogEntity>
    fun deleteAllByUserIdentifier(userIdentifier: String)
}
