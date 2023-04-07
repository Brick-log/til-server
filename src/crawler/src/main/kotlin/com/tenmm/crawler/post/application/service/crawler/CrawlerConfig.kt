package com.tenmm.crawler.post.application.service.crawler

import com.tenmm.crawler.util.UrlType
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CrawlerConfig {

    @Bean
    fun crawlerMap(): CrawlerMap {
        return mapOf(
            UrlType.TISTORY to TistoryCrawlingService(),
            UrlType.NAVER to NaverCrawlingService(),
            UrlType.VELOG to VelogCrawlingService(),
            UrlType.MEDIUM to MediumCrawlingService(),
            UrlType.ETC to EtcCrawlingService()
        )
    }
}

typealias CrawlerMap = Map<UrlType, CrawlerMetaService>
