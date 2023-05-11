package com.tenmm.tilserver.outbound.persistence.repository

import com.tenmm.tilserver.outbound.persistence.entity.RecommendPostEntity
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface RecommendPostRepository : JpaRepository<RecommendPostEntity, Long> {
    @Query(value = "SELECT * FROM recommended_post order by RAND() limit :size", nativeQuery = true)
    fun findByRandom(size: Int): List<RecommendPostEntity>

    fun findByCategoryIdentifierOrderByCreatedAtDesc(
        categoryIdentifier: String,
        pageable: Pageable,
    ): List<RecommendPostEntity>
}
