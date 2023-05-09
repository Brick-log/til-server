package com.tenmm.tilserver.crawler.application.outbound

import com.tenmm.tilserver.crawler.domain.CssSelectorInfo
import com.tenmm.tilserver.crawler.domain.Post
import com.tenmm.tilserver.crawler.domain.Url

interface DoCrawlingPort {
    fun crawling(
        url: Url,
        cssSelectorInfo: CssSelectorInfo,
    ): Post
}
