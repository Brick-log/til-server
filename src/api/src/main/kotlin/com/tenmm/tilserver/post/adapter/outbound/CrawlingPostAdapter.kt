package com.tenmm.tilserver.post.adapter.outbound

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.common.domain.Url
import com.tenmm.tilserver.post.application.outbound.CrawlingPostPort
import com.tenmm.tilserver.protocol.CrawlerServiceGrpcKt
import com.tenmm.tilserver.protocol.CrawlingRequest

// @Component
class CrawlingPostAdapter(
    private val crawlerServiceCoroutineStub: CrawlerServiceGrpcKt.CrawlerServiceCoroutineStub,
) : CrawlingPostPort {
    override suspend fun requestParseFromUrl(url: Url, userIdentifier: Identifier): Identifier {
        val request = CrawlingRequest.newBuilder()
            .setUrl(url.value)
            .setUserIdentifier(userIdentifier.value)
            .build()
        val response = crawlerServiceCoroutineStub.doCrawling(request)
        return Identifier(response.identifier)
    }
}
