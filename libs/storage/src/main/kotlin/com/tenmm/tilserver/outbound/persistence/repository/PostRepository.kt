package com.tenmm.tilserver.outbound.persistence.repository

import com.tenmm.tilserver.outbound.persistence.entity.PostEntity
import java.sql.Timestamp
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface PostRepository : JpaRepository<PostEntity, Long> {
    @Query(value = "SELECT * FROM post order by RAND() limit :size", nativeQuery = true)
    fun findByRandom(size: Int): List<PostEntity>

    fun findByIdentifier(identifier: String): PostEntity?
    fun findAllByIdentifierIn(identifiers: List<String>): List<PostEntity>

    @Query(
        value =
        """
        SELECT * FROM post 
        where category_identifier = :identifier 
        ORDER BY created_at DESC, id DESC
        LIMIT :size
        """,
        nativeQuery = true
    )
    fun findAllByCategoryIdentifier(identifier: String, size: Int): List<PostEntity>

    @Query(
        value =
        """
        SELECT * FROM post 
        where category_identifier = :categoryIdentifier 
        AND ((created_at = :lastEntityCreatedAt AND id < :lastEntityId) OR created_at < :lastEntityCreatedAt)
        ORDER BY created_at DESC, id DESC
        LIMIT :size
        """,
        nativeQuery = true
    )
    fun findAllByCreatedAtBeforeAndCategoryIdentifier(
        categoryIdentifier: String,
        lastEntityId: Int,
        lastEntityCreatedAt: Timestamp,
        size: Int,
    ): List<PostEntity>

    @Query(
        value =
        """
        SELECT * FROM post 
        where user_identifier = :userIdentifier 
        AND created_at >= :from
        AND created_at < :to
        ORDER BY created_at DESC, id DESC
        LIMIT :size
        """,
        nativeQuery = true
    )
    fun findByPostListByUserIdentifierAndTimePeriodWithSize(
        userIdentifier: String,
        from: Timestamp,
        to: Timestamp,
        size: Int,
    ): List<PostEntity>

    @Query(
        value =
        """
        SELECT * FROM post 
        where user_identifier = :userIdentifier
        AND ((created_at = :lastEntityCreatedAt AND id < :lastEntityId) OR created_at < :lastEntityCreatedAt)
        AND created_at >= :from
        AND created_at < :to
        ORDER BY created_at DESC, id DESC
        LIMIT :size
        """,
        nativeQuery = true
    )
    fun findByPostListByUserIdentifierAndTimePeriodAndWithSizeUsingPageToken(
        userIdentifier: String,
        lastEntityId: Int,
        lastEntityCreatedAt: Timestamp,
        from: Timestamp,
        to: Timestamp,
        size: Int,
    ): List<PostEntity>

    @Query(
        value =
        """
        SELECT * FROM post 
        where user_identifier = :userIdentifier 
        AND created_at >= :from
        AND created_at < :to
        ORDER BY created_at DESC, id DESC
        """,
        nativeQuery = true
    )
    fun findByPostListByUserIdentifierAndTimePeriod(
        userIdentifier: String,
        from: Timestamp,
        to: Timestamp,
    ): List<PostEntity>

    fun deleteByIdentifier(identifier: String): Int

    fun countAllByUserIdentifierAndCreatedAtGreaterThanEqualAndCreatedAtLessThan(
        userIdentifier: String,
        from: Timestamp,
        to: Timestamp,
    ): Int

    fun countAllByUserIdentifier(userIdentifier: String): Int
    fun countAllByCategoryIdentifier(categoryIdentifier: String): Int
}
