package com.tenmm.crawler.application.outbound

import com.tenmm.crawler.domain.Crawling

interface SaveCrawlingPort {
    fun saveCrawling(crawling: Crawling): String
}
