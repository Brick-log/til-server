package com.tenmm.tilserver.outbound.persistence.repository

import com.tenmm.tilserver.outbound.persistence.entity.RecommendRetrospectEntity
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface RecommendRetrospectRepository : JpaRepository<RecommendRetrospectEntity, Long> {
    @Query(value = "SELECT * FROM recommended_retrospect order by RAND() limit :size", nativeQuery = true)
    fun findByRandom(size: Int): List<RecommendRetrospectEntity>

    fun findByCategoryIdentifierOrderByCreatedAtDesc(
        categoryIdentifier: String,
        pageable: Pageable,
    ): List<RecommendRetrospectEntity>
}
