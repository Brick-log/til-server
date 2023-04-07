package com.tenmm.crawler.post.application.inbound

import com.tenmm.crawler.post.domain.Identifier
import com.tenmm.crawler.post.domain.Url

interface DoCrawlingUseCase {
    fun invoke(url: Url, userIdentifier: Identifier): Identifier
}
