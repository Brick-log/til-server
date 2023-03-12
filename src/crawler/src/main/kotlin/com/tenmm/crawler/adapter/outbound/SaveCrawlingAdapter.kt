package com.tenmm.crawler.adapter.outbound

import com.tenmm.crawler.application.outbound.SaveCrawlingPort
import com.tenmm.crawler.domain.Crawling
import com.tenmm.tilserver.outbound.persistence.repository.CrawlingRepository
import com.tenmm.tilserver.outbound.persistence.entity.CrawlingEntity
import java.util.UUID
import org.springframework.stereotype.Component

@Component
class SaveCrawlingAdapter(
    private val crawlingRepository: CrawlingRepository
) : SaveCrawlingPort {
    override fun saveCrawling(crawling: Crawling): String {
        val crawlingEntity: CrawlingEntity = CrawlingEntity(
            identifier = UUID.randomUUID().toString(),
            title = crawling.title,
            createdAt = crawling.createdAt,
            description = crawling.description,
        )
        crawlingRepository.save(crawlingEntity)
        return crawlingEntity.identifier
    }
}
