package com.tenmm.crawler.adapter.outbound

import com.tenmm.crawler.application.outbound.SaveCrawlingPort
import com.tenmm.crawler.domain.Crawling
// import com.tenmm.tilserver.outbound.persistence.repository.ParsedPostRepository
import com.tenmm.tilserver.outbound.persistence.entity.ParsedPostEntity
import java.util.UUID
import org.springframework.stereotype.Component

@Component
class SaveCrawlingAdapter(
    // private val parsedPostRepository: ParsedPostRepository
) : SaveCrawlingPort {
    override fun saveCrawling(crawling: Crawling, userIdentifier: String): String {
        val parsedPostEntity: ParsedPostEntity = ParsedPostEntity(
            identifier = UUID.randomUUID().toString(),
            title = crawling.title,
            createdAt = crawling.createdAt,
            description = crawling.description,
            url = crawling.url,
            userIdentifier = userIdentifier,
        )
        // parsedPostRepository.save(parsedPostEntity)
        return parsedPostEntity.identifier
    }
}
