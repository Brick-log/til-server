package com.tenmm.tilserver.outbound.persistence.repository

import com.tenmm.tilserver.outbound.persistence.entity.CrawlingEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CrawlingRepository : CrudRepository<CrawlingEntity, String> {
    fun save(crawlingEntity: CrawlingEntity)
}
