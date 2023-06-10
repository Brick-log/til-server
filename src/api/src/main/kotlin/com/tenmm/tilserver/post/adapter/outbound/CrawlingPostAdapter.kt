package com.tenmm.tilserver.post.adapter.outbound

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.common.domain.Url
import com.tenmm.tilserver.common.domain.toIdentifier
import com.tenmm.tilserver.crawler.application.inbound.DoCrawlingUseCase
import com.tenmm.tilserver.post.application.outbound.CrawlingPostPort
import org.springframework.stereotype.Component

@Component
class CrawlingPostAdapter(
    private val doCrawlingUseCase: DoCrawlingUseCase,
) : CrawlingPostPort {
    override suspend fun requestParseFromUrl(url: Url, userIdentifier: Identifier): Identifier {
        val response = doCrawlingUseCase.invoke(
            Url(url.value),
            userIdentifier.value.toIdentifier()
        )
        return response.value.toIdentifier()
    }
}
