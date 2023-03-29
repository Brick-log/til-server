package com.tenmm.crawler.application.inbound

interface SaveCrawlingUseCase {
    fun saveCrawling(url: String, userIdentifier: String): String
}
