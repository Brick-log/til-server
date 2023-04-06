package com.tenmm.crawler.post.application.service.crawler

class TistoryCrawlingService : CrawlerMetaService() {
    override fun getTitleCssSelector(): String {
        return "meta[property=og:title]"
    }

    override fun getDescriptionCssSelector(): String {
        return "meta[property=og:description]"
    }

    override fun getCreatedAtCssSelector(): String {
        return "meta[property=article:published_time]"
    }

    override fun getDateFormat(): String {
        return "yyyy-MM-dd'T'HH:mm:ssXXX"
    }
}
