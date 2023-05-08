package com.tenmm.tilserver.post.application.outbound

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.post.application.outbound.model.ParsedPostResult

interface GetParsedPostPort {
    suspend fun findByIdentifier(userIdentifier: Identifier, parsedPostIdentifier: Identifier): ParsedPostResult?
}
