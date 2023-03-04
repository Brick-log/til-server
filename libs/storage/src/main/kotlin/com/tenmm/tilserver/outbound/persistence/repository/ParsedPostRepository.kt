package com.tenmm.tilserver.outbound.persistence.repository

import com.tenmm.tilserver.outbound.persistence.entity.ParsedPostEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ParsedPostRepository : CrudRepository<ParsedPostEntity, String> {
    fun findByIdentifier(identifier: String): ParsedPostEntity?
    fun deleteByIdentifier(identifier: String): Boolean
}
