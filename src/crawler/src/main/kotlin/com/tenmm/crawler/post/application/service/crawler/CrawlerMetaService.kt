package com.tenmm.crawler.post.application.service.crawler

import com.tenmm.crawler.post.domain.CssSelectorInfo

abstract class CrawlerMetaService {
    fun getMeta(): CssSelectorInfo {
        return CssSelectorInfo(
            titleCssSelector = getTitleCssSelector(),
            descriptionCssSelector = getDescriptionCssSelector(),
            createdAtCssSelector = getCreatedAtCssSelector(),
            dateFormat = getDateFormat()
        )
    }

    abstract fun getTitleCssSelector(): String
    abstract fun getDescriptionCssSelector(): String
    abstract fun getCreatedAtCssSelector(): String
    abstract fun getDateFormat(): String
}
