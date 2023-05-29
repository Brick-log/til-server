package com.tenmm.tilserver.crawler.application.inbound

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.common.domain.Url

interface DoCrawlingUseCase {
    suspend fun invoke(url: Url, userIdentifier: Identifier): Identifier
}
