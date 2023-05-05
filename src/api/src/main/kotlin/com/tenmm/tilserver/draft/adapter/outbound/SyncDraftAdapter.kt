package com.tenmm.tilserver.draft.adapter.outbound

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.draft.application.outbound.SyncDraftPort
import com.tenmm.tilserver.draft.domain.Draft
import kotlinx.coroutines.reactive.awaitFirstOrNull
import mu.KotlinLogging
import org.springframework.data.redis.core.ReactiveRedisTemplate
import org.springframework.data.redis.core.ScanOptions
import org.springframework.data.redis.core.deleteAndAwait
import org.springframework.data.redis.core.getAndAwait
import org.springframework.data.redis.core.setAndAwait
import org.springframework.stereotype.Component

private val logger = KotlinLogging.logger {}
@Component
class SyncDraftAdapter(
    private val draftRedisTemplate: ReactiveRedisTemplate<String, String>,
) : SyncDraftPort {

    private val ops = draftRedisTemplate.opsForValue()
    private val keyPrefix = "draft:"

    private fun generateKey(userIdentifier: Identifier): String {
        return "${keyPrefix}${userIdentifier.value}"
    }
    override suspend fun save(userIdentifier: Identifier, data: String): Boolean {
        val key = generateKey(userIdentifier)
        return ops.setAndAwait(key, data)
    }

    override suspend fun findByUserIdentifier(userIdentifier: Identifier): Draft? {
        val key = generateKey(userIdentifier)
        val data = ops.getAndAwait(key)

        return data?.let {
            return Draft(
                data = it,
                userIdentifier = userIdentifier
            )
        }
    }

    override suspend fun findDraftsWithCount(size: Int): List<Draft> {
        val scanOptions: ScanOptions = ScanOptions.scanOptions().match("$keyPrefix*").count(size.toLong()).build()

        val keys = draftRedisTemplate
            .scan(scanOptions)
            .collectList()
            .awaitFirstOrNull() ?: emptyList<String>()

        if (keys.isEmpty()) {
            return emptyList()
        }
        val values = ops.multiGet(keys).awaitFirstOrNull() ?: emptyList()

        return keys.indices.map { index ->
            val userIdentifier = keys[index].substringAfter(keyPrefix)
            val value = values[index]
            Draft(
                userIdentifier = Identifier(userIdentifier),
                data = value
            )
        }
    }

    override suspend fun delete(draft: Draft): Boolean {
        val key = "${keyPrefix}${draft.userIdentifier.value}"
        return draftRedisTemplate.deleteAndAwait(key).toInt() == 1
    }

    override suspend fun delete(drafts: List<Draft>): Boolean {
        if (drafts.isEmpty()) {
            logger.info("Delete Sync Draft Target Size: Empty")
            return true
        }

        val keys = drafts.map { draft -> "${keyPrefix}${draft.userIdentifier.value}" }.toTypedArray()
        val deletedKeySize = draftRedisTemplate.deleteAndAwait(*keys).toInt()

        logger.info("Delete Sync Draft Target Size: $deletedKeySize")

        return deletedKeySize == drafts.size
    }
}
