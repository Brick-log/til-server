package com.tenmm.tilserver.outbound.persistence.repository

import com.tenmm.tilserver.outbound.persistence.entity.RecommendPostEntity
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RecommendPostRepository : JpaRepository<RecommendPostEntity, Long> {
    fun findByCategoryIdentifierOrderByCreatedAtDesc(
        categoryIdentifier: String,
        pageable: Pageable,
    ): List<RecommendPostEntity>
}
