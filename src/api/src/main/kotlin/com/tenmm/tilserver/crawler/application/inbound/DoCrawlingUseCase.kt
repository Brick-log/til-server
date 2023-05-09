package com.tenmm.tilserver.crawler.application.inbound

import com.tenmm.tilserver.crawler.domain.Identifier
import com.tenmm.tilserver.crawler.domain.Url

interface DoCrawlingUseCase {
    suspend fun invoke(url: Url, userIdentifier: Identifier): Identifier
}
