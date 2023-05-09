package com.tenmm.tilserver.crawler.adapter.outbound

import com.tenmm.tilserver.crawler.application.outbound.SaveCrawlingPort
import com.tenmm.tilserver.crawler.domain.Identifier
import com.tenmm.tilserver.crawler.domain.Post
import com.tenmm.tilserver.outbound.redis.entity.ParsedPostEntity
import org.springframework.data.redis.core.ReactiveRedisTemplate
import org.springframework.data.redis.core.setAndAwait
import org.springframework.stereotype.Component

@Component
class SaveCrawlingAdapter(
    parsedPostRedisTemplate: ReactiveRedisTemplate<String, ParsedPostEntity>,
) : SaveCrawlingPort {
    private val ops = parsedPostRedisTemplate.opsForValue()

    override suspend fun saveCrawling(userIdentifier: Identifier, post: Post): Identifier {
        val parsedPostIdentifier = Identifier.generate()
        val parsedPostEntity = ParsedPostEntity(
            identifier = parsedPostIdentifier.value,
            userIdentifier = userIdentifier.value,
            title = post.title,
            createdAt = post.createdAt,
            description = post.description,
            url = post.url.value,
        )
        val key = ParsedPostEntity.generateKey(
            userIdentifier = userIdentifier.value,
            identifier = parsedPostIdentifier.value
        )
        ops.setAndAwait(key, parsedPostEntity)
        return parsedPostIdentifier
    }
}
