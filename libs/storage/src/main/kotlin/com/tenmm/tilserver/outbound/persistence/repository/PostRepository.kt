package com.tenmm.tilserver.outbound.persistence.repository

import com.tenmm.tilserver.outbound.persistence.entity.PostEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PostRepository : JpaRepository<PostEntity, Long> {
    fun findByIdentifier(identifier: String): PostEntity?
    fun findAllByCategoryIdentifier(categoryIdentifier: String): List<PostEntity>
    fun deleteByIdentifier(identifier: String): Boolean
}
