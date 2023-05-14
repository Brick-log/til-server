package com.tenmm.tilserver.outbound.persistence.repository

import com.tenmm.tilserver.outbound.persistence.entity.ParsedPostEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ParsedPostRepository : JpaRepository<ParsedPostEntity, Long> {
    fun findByIdentifier(identifier: String): ParsedPostEntity?
    fun deleteByIdentifier(identifier: String): Int
}
