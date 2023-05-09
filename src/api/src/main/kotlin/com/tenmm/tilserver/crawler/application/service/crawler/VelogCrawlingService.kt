package com.tenmm.tilserver.crawler.application.service.crawler

class VelogCrawlingService : CrawlerMetaService() {
    override fun getTitleCssSelector(): String {
        return "meta[property=og:title]"
    }

    override fun getDescriptionCssSelector(): String {
        return "meta[property=og:description]"
    }

    override fun getCreatedAtCssSelector(): String {
        return "div.information > span:nth-child(3)"
    }

    override fun getDateFormat(): String {
        return "yyyy년 MM월 dd일"
    }
}
