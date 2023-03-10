package com.tenmm.tilserver.post.adapter.outbound

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.outbound.persistence.repository.ParsedPostRepository
import com.tenmm.tilserver.post.application.outbound.ParsedPostPort
import com.tenmm.tilserver.post.application.outbound.model.ParsedPostResult
import mu.KotlinLogging
import org.springframework.stereotype.Component

private val logger = KotlinLogging.logger {}

@Component
class ParsedPostAdapter(
    private val parsedPostRepository: ParsedPostRepository,
) : ParsedPostPort {
    override fun getByIdentifier(identifier: Identifier): ParsedPostResult? {
        return try {
            parsedPostRepository.findByIdentifier(identifier = identifier.value)?.toResult()
        } catch (e: Exception) {
            logger.error(e) { "ParsedPost get Fail - $identifier" }
            null
        }
    }

    override fun deleteByIdentifier(identifier: Identifier): Boolean {
        return try {
            parsedPostRepository.deleteByIdentifier(identifier = identifier.value)
        } catch (e: Exception) {
            logger.error(e) { "ParsedPost delete Fail - $identifier" }
            false
        }
    }
}
