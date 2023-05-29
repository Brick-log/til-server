package com.tenmm.tilserver.crawler.application.service.crawler

import java.text.SimpleDateFormat

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

    override fun getDateFormat(): SimpleDateFormat {
        return SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX")
    }
}
