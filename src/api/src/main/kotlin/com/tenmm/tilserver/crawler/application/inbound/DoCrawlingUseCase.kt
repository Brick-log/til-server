package com.tenmm.tilserver.crawler.application.inbound

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.common.domain.Url
import com.tenmm.tilserver.crawler.domain.ParsedPost

interface DoCrawlingUseCase {
    suspend fun invoke(url: Url, userIdentifier: Identifier): ParsedPost
}
