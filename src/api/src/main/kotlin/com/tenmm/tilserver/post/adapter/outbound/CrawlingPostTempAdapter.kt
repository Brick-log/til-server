package com.tenmm.tilserver.post.adapter.outbound

import com.tenmm.tilserver.crawler.application.inbound.DoCrawlingUseCase
import com.tenmm.tilserver.post.application.outbound.CrawlingPostPort
import org.springframework.stereotype.Component
import com.tenmm.tilserver.common.domain.Identifier as CommonIdentifier
import com.tenmm.tilserver.common.domain.Url as CommonUrl
import com.tenmm.tilserver.crawler.domain.Identifier as CrawlerIdentifier
import com.tenmm.tilserver.crawler.domain.Url as CrawlerUrl

@Component
class CrawlingPostTempAdapter(
    private val doCrawlingUseCase: DoCrawlingUseCase,
) : CrawlingPostPort {
    override suspend fun requestParseFromUrl(url: CommonUrl, userIdentifier: CommonIdentifier): CommonIdentifier {
        val response = doCrawlingUseCase.invoke(CrawlerUrl(url.value), CrawlerIdentifier(userIdentifier.value))
        return CommonIdentifier(response.value)
    }
}
