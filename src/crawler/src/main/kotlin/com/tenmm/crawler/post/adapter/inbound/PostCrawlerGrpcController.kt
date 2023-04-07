package com.tenmm.crawler.post.adapter.inbound

import com.tenmm.crawler.post.application.inbound.DoCrawlingUseCase
import com.tenmm.crawler.post.domain.Identifier
import com.tenmm.crawler.post.domain.Url
import com.tenmm.tilserver.protocol.CrawlerServiceGrpcKt
import com.tenmm.tilserver.protocol.CrawlingRequest
import com.tenmm.tilserver.protocol.CrawlingResponse
import org.springframework.stereotype.Component

/**
 * TODO
 * 1. Crawler 작성
 *     1.1. Tistory Crawling (title, dttm, description)
 *     1.2. Naver Crawling (title, dttm, description)
 *     1.3. Youtube Crawling (title, dttm, description)
 * 2. crawling 결과 redis에 저장
 *     2.1. UUID 생성
 *     2.2. redis entity 생성
 *     2.3. redis에 repository 생성
 */
@Component
class PostCrawlerGrpcController(
    private val doCrawlingUseCase: DoCrawlingUseCase,
) : CrawlerServiceGrpcKt.CrawlerServiceCoroutineImplBase() {
    override suspend fun doCrawling(request: CrawlingRequest): CrawlingResponse {
        val identifier = doCrawlingUseCase.invoke(Url(request.url), Identifier(request.userIdentifier))

        return CrawlingResponse.newBuilder()
            .setIdentifier(identifier.value)
            .build()
    }
}
