package com.tenmm.tilserver.outbound.persistence.repository

import com.tenmm.tilserver.outbound.persistence.entity.RetrospectEntity
import java.sql.Timestamp
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface RetrospectRepository : JpaRepository<RetrospectEntity, Long> {
    fun findByUserIdentifier(userIdentifier: String): List<RetrospectEntity>
    fun findOneByRetrospectIdentifier(retrospectIdentifier: String): RetrospectEntity
    fun findByUserIdentifierAndRetrospectIdentifier(userIdentifier: String, retrospectIdentifier: String): RetrospectEntity?
    fun deleteByUserIdentifierAndRetrospectIdentifier(userIdentifier: String, retrospectIdentifier: String)
    fun countAllByUserIdentifier(userIdentifier: String): Int

    @Query(
        value =
        """
        SELECT * FROM retrospect 
        where user_identifier = :userIdentifier 
        AND created_at >= :from
        AND created_at < :to
        ORDER BY created_at DESC, id DESC
        """,
        nativeQuery = true
    )
    fun findByRetrospectListByUserIdentifierAndTimePeriod(
        userIdentifier: String,
        to: Timestamp,
        from: Timestamp,
    ): List<RetrospectEntity>

    @Query(
        value =
        """
        SELECT * FROM retrospect 
        where user_identifier = :userIdentifier 
        AND created_at >= :from
        AND created_at < :to
        ORDER BY created_at DESC, id DESC
        LIMIT :size
        """,
        nativeQuery = true
    )
    fun findRetrospectListbyUserIdentifierAndTimePeriodWithSize(
        userIdentifier: String,
        from: Timestamp,
        to: Timestamp,
        size: Int,
    ): List<RetrospectEntity>

    @Query(
        value =
        """
        SELECT * FROM retrospect 
        where user_identifier = :userIdentifier 
        AND created_at >= :from
        AND created_at < :to
        """,
        nativeQuery = true
    )
    fun findOneRetrospectByUserIdentifierToday(
        userIdentifier: String,
        from: Timestamp,
        to: Timestamp,
    ):  List<RetrospectEntity>

    @Query(
        value =
        """
        SELECT * FROM retrospect 
        where categoryIdentifier = :categoryIdentifier 
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
    ): List<RetrospectEntity>

    @Query(
        value =
        """
        SELECT * FROM retrospect 
        where ((created_at = :lastEntityCreatedAt AND id < :lastEntityId) OR created_at < :lastEntityCreatedAt)
        ORDER BY created_at DESC, id DESC
        LIMIT :size
        """,
        nativeQuery = true
    )
    fun findAllByCreatedAtBefore(
        lastEntityId: Int,
        lastEntityCreatedAt: Timestamp,
        size: Int,
    ): List<RetrospectEntity>

    @Query(
        value =
        """
        SELECT * FROM retrospect 
        LIMIT :size
        """,
        nativeQuery = true
    )
    fun findAllWithSize (
        size: Int,
    ): List<RetrospectEntity>

    @Query(
        value =
        """
        SELECT * FROM retrospect 
        where categoryIdentifier = :categoryIdentifier
        LIMIT :size
        """,
        nativeQuery = true
    )
    fun findByCategoryIdentifierWithSize (
        size: Int,
        categoryIdentifier: String,
    ): List<RetrospectEntity>
}