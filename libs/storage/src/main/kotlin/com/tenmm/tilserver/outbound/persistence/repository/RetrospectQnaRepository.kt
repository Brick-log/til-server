package com.tenmm.tilserver.outbound.persistence.repository

import com.tenmm.tilserver.outbound.persistence.entity.RetrospectQnaEntity
import java.sql.Timestamp
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface RetrospectQnaRepository : JpaRepository<RetrospectQnaEntity, Long> {
    fun findByRetrospectIdentifier(retrospectIdentifier: String): List<RetrospectQnaEntity>
    fun deleteByRetrospectIdentifier(retrospectIdentifier: String)
}