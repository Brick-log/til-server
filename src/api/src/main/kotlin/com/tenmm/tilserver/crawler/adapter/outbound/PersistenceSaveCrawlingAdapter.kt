package com.tenmm.tilserver.crawler.adapter.outbound

import com.tenmm.tilserver.crawler.application.outbound.SaveCrawlingPort
import com.tenmm.tilserver.crawler.domain.Identifier
import com.tenmm.tilserver.crawler.domain.Post
import com.tenmm.tilserver.outbound.persistence.entity.ParsedPostEntity
import com.tenmm.tilserver.outbound.persistence.repository.ParsedPostRepository
import org.springframework.stereotype.Component

@Component
class PersistenceSaveCrawlingAdapter(
    private val parsedPostRepository: ParsedPostRepository,
) : SaveCrawlingPort {
    override suspend fun saveCrawling(userIdentifier: Identifier, post: Post): Identifier {
        val entity = ParsedPostEntity(
            identifier = Identifier.generate().value,
            userIdentifier = userIdentifier.value,
            url = post.url.value,
            title = post.title,
            description = post.description,
            createdAt = post.createdAt
        )
        parsedPostRepository.save(entity)
        return Identifier(entity.identifier)
    }
}
