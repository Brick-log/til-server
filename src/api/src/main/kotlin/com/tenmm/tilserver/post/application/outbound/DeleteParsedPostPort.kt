package com.tenmm.tilserver.post.application.outbound

import com.tenmm.tilserver.common.domain.Identifier

interface DeleteParsedPostPort {
    suspend fun deleteByIdentifier(userIdentifier: Identifier, parsedPostIdentifier: Identifier): Boolean
}
