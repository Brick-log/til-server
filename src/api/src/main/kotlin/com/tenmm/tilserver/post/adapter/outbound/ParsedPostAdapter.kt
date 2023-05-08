package com.tenmm.tilserver.post.adapter.outbound

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.outbound.redis.entity.ParsedPostEntity
import com.tenmm.tilserver.post.application.outbound.DeleteParsedPostPort
import com.tenmm.tilserver.post.application.outbound.GetParsedPostPort
import com.tenmm.tilserver.post.application.outbound.model.ParsedPostResult
import org.springframework.data.redis.core.ReactiveRedisTemplate
import org.springframework.data.redis.core.deleteAndAwait
import org.springframework.data.redis.core.getAndAwait
import org.springframework.stereotype.Component

@Component
class ParsedPostAdapter(
    parsedPostRedisTemplate: ReactiveRedisTemplate<String, ParsedPostEntity>,
) : GetParsedPostPort, DeleteParsedPostPort {

    private val ops = parsedPostRedisTemplate.opsForValue()

    override suspend fun findByIdentifier(
        userIdentifier: Identifier,
        parsedPostIdentifier: Identifier,
    ): ParsedPostResult? {
        val key = ParsedPostEntity.generateKey(
            userIdentifier = userIdentifier.value,
            identifier = parsedPostIdentifier.value
        )
        return ops.getAndAwait(key)?.toResult()
    }

    override suspend fun deleteByIdentifier(
        userIdentifier: Identifier,
        parsedPostIdentifier: Identifier,
    ): Boolean {
        val key = ParsedPostEntity.generateKey(
            userIdentifier = userIdentifier.value,
            identifier = parsedPostIdentifier.value
        )
        return ops.deleteAndAwait(key)
    }
}
