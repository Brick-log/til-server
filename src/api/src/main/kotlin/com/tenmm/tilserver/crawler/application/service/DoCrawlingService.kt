package com.tenmm.tilserver.crawler.application.service

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.common.domain.Url
import com.tenmm.tilserver.crawler.application.inbound.DoCrawlingUseCase
import com.tenmm.tilserver.crawler.application.outbound.DoCrawlingPort
import com.tenmm.tilserver.crawler.application.outbound.SaveCrawlingPort
import com.tenmm.tilserver.crawler.application.service.crawler.CrawlerMap
import com.tenmm.tilserver.crawler.util.UrlCheck
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
