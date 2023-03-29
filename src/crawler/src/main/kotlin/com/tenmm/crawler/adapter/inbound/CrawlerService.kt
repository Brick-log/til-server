package com.tenmm.crawler.adapter.inbound

import com.tenmm.tilserver.protocol.CrawlerServiceGrpcKt
import com.tenmm.tilserver.protocol.CrawlingRequest
import com.tenmm.tilserver.protocol.CrawlingResponse
import org.springframework.stereotype.Component
import com.tenmm.crawler.application.inbound.SaveCrawlingUseCase
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
class CrawlerService(
    private val saveCrawlingUseCase: SaveCrawlingUseCase
) : CrawlerServiceGrpcKt.CrawlerServiceCoroutineImplBase() {
    override suspend fun doCrawling(request: CrawlingRequest): CrawlingResponse {
        val identifier = saveCrawlingUseCase.saveCrawling(
            url = request.url,
            userIdentifier = "913115be-5b64-491e-bcfb-d5e724f25642"
        )
        return CrawlingResponse.newBuilder()
            .setIdentifier(identifier)
            .build()
    }
}
