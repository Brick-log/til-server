package com.tenmm.tilserver.post.adapter.outbound

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.outbound.persistence.repository.ParsedPostRepository
import com.tenmm.tilserver.post.application.outbound.DeleteParsedPostPort
import com.tenmm.tilserver.post.application.outbound.GetParsedPostPort
import com.tenmm.tilserver.post.application.outbound.model.ParsedPostResult
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class PersistenceParsedPostAdapter(
    private val parsedPostRepository: ParsedPostRepository,
) : GetParsedPostPort, DeleteParsedPostPort {

    override suspend fun findByIdentifier(
        userIdentifier: Identifier,
        parsedPostIdentifier: Identifier,
    ): ParsedPostResult? {
        return parsedPostRepository.findByIdentifier(identifier = parsedPostIdentifier.value)?.toResult()
    }

    @Transactional
    override suspend fun deleteByIdentifier(
        userIdentifier: Identifier,
        parsedPostIdentifier: Identifier,
    ): Boolean {
        return parsedPostRepository.deleteByIdentifier(parsedPostIdentifier.value) == 1
    }
}
