package com.tenmm.tilserver.outbound.persistence.repository

import com.tenmm.tilserver.outbound.persistence.entity.PostEntity
import java.sql.Timestamp
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PostRepository : JpaRepository<PostEntity, Long> {
    fun findByIdentifier(identifier: String): PostEntity?
    fun findAllByIdentifierIn(identifiers: List<String>): List<PostEntity>

    fun findAllByCategoryIdentifier(identifier: String, pageable: Pageable): List<PostEntity>
    fun findAllByIdLessThanAndCategoryIdentifier(
        id: Long,
        categoryIdentifier: String,
        pageable: Pageable,
    ): List<PostEntity>

    fun findAllByUserIdentifierAndCreatedAtGreaterThanEqualAndCreatedAtLessThan(
        userIdentifier: String,
        from: Timestamp,
        to: Timestamp,
        pageable: Pageable,
    ): List<PostEntity>

    fun findAllByIdLessThanAndUserIdentifierAndCreatedAtGreaterThanEqualAndCreatedAtLessThan(
        id: Long,
        userIdentifier: String,
        from: Timestamp,
        to: Timestamp,
        pageable: Pageable,
    ): List<PostEntity>

    fun findAllByUserIdentifierAndCreatedAtGreaterThanEqualAndCreatedAtLessThan(
        userIdentifier: String,
        from: Timestamp,
        to: Timestamp,
    ): List<PostEntity>

    fun deleteByIdentifier(identifier: String): Boolean
    fun countAllByUserIdentifierAndCreatedAtGreaterThanEqualAndCreatedAtLessThan(
        userIdentifier: String,
        from: Timestamp,
        to: Timestamp,
    ): Int
}
