package com.tenmm.crawler.post.application.service

import com.tenmm.crawler.post.application.inbound.DoCrawlingUseCase
import com.tenmm.crawler.post.application.outbound.DoCrawlingPort
import com.tenmm.crawler.post.application.outbound.SaveCrawlingPort
import com.tenmm.crawler.post.application.service.crawler.CrawlerMap
import com.tenmm.crawler.post.domain.Identifier
import com.tenmm.crawler.post.domain.Url
import com.tenmm.crawler.util.UrlCheck
import org.springframework.stereotype.Service

@Service
class DoCrawlingService(
    private val crawlerMap: CrawlerMap,
    private val doCrawlingPort: DoCrawlingPort,
    private val saveCrawlingPort: SaveCrawlingPort,
) : DoCrawlingUseCase {
    override suspend fun invoke(url: Url, userIdentifier: Identifier): Identifier {
        val type = UrlCheck.getType(url)
        val cssSelectorInfo = crawlerMap[type]!!.getMeta()

        val crawlingResult = doCrawlingPort.crawling(
            url = url,
            cssSelectorInfo = cssSelectorInfo
        )

        return saveCrawlingPort.saveCrawling(userIdentifier, crawlingResult)
    }
}
