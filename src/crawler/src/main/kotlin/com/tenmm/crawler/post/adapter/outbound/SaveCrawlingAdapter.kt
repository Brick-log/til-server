package com.tenmm.crawler.post.adapter.outbound

import com.tenmm.crawler.post.adapter.outbound.repository.SaveCrawlingRepository
import com.tenmm.crawler.post.application.outbound.SaveCrawlingPort
import com.tenmm.crawler.post.domain.Identifier
import com.tenmm.crawler.post.domain.Post
import com.tenmm.tilserver.outbound.persistence.entity.ParsedPostEntity
import org.springframework.stereotype.Component

@Component
class SaveCrawlingAdapter(
    private val saveCrawlingRepository: SaveCrawlingRepository,
) : SaveCrawlingPort {
    override fun saveCrawling(userIdentifier: Identifier, post: Post): Identifier {
        val identifier = Identifier.generate()
        val parsedPostEntity = ParsedPostEntity(
            identifier = identifier.value,
            userIdentifier = userIdentifier.value,
            title = post.title,
            createdAt = post.createdAt,
            description = post.description,
            url = post.url.value,
        )
        saveCrawlingRepository.save(parsedPostEntity)
        return identifier
    }
}
