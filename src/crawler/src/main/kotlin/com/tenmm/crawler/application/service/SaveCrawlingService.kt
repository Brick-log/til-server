package com.tenmm.crawler.application.service

import com.tenmm.crawler.application.inbound.SaveCrawlingUseCase
import org.springframework.stereotype.Service
import com.tenmm.crawler.util.UrlCheck
import com.tenmm.crawler.util.UrlType
import com.tenmm.crawler.domain.Crawling
import com.tenmm.crawler.application.service.crawler.TistoryCrawlingService
import com.tenmm.crawler.application.service.crawler.NaverCrawlingService
import com.tenmm.crawler.application.service.crawler.VelogCrawlingService
import com.tenmm.crawler.application.service.crawler.MediumCrawlingService
import com.tenmm.crawler.application.service.crawler.EtcCrawlingService
import com.tenmm.crawler.application.outbound.SaveCrawlingPort

@Service
class SaveCrawlingService(
    private val saveCrawlingPort: SaveCrawlingPort
) : SaveCrawlingUseCase {
    override fun saveCrawling(url: String, userIdentifier: String): String {
        var crawling: Crawling

        when (UrlCheck.getDomain(url)) {
            UrlType.TISTORY.name -> {
                crawling = TistoryCrawlingService.crawling(url)
            }
            UrlType.NAVER.name -> {
                crawling = NaverCrawlingService.crawling(url)
            }
            UrlType.VELOG.name -> {
                crawling = VelogCrawlingService.crawling(url)
            }
            UrlType.MEDIUM.name -> {
                crawling = MediumCrawlingService.crawling(url)
            }
            else -> {
                crawling = EtcCrawlingService.crawling(url)
            }
        }
        print(crawling)
        return saveCrawlingPort.saveCrawling(crawling, userIdentifier)
    }
}
