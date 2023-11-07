package com.tenmm.tilserver.outbound.persistence.repository

import com.tenmm.tilserver.outbound.persistence.entity.RetrospectEntity
import java.sql.Timestamp
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface RetrospectRepository : JpaRepository<RetrospectEntity, Long> {
    fun findByUserIdentifier(userIdentifier: String): List<RetrospectEntity>
    fun findByUserIdentifierAndRetrospectIdentifier(userIdentifier: String, retrospectIdentifier: String): RetrospectEntity?
    fun deleteByUserIdentifierAndRetrospectIdentifier(userIdentifier: String, retrospectIdentifier: String)
    
    @Query(
        value =
        """
        SELECT * FROM retrospect 
        where user_identifier = :userIdentifier 
        AND created_at >= :from
        AND created_at < :to
        AND is_secret = FALSE
        ORDER BY created_at DESC, id DESC
        """,
        nativeQuery = true
    )
    fun findByRetrospectListByUserIdentifierAndTimePeriodAndSecretIsFalse(
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
    ): RetrospectEntity?

    fun countAllByUserIdentifier(userIdentifier: String): Int
}