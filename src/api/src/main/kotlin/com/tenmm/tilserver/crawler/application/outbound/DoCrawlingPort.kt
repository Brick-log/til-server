package com.tenmm.tilserver.crawler.application.outbound

import com.tenmm.tilserver.common.domain.Url
import com.tenmm.tilserver.crawler.domain.CssSelectorInfo
import com.tenmm.tilserver.crawler.domain.Post

interface DoCrawlingPort {
    fun crawling(
        url: Url,
        cssSelectorInfo: CssSelectorInfo,
    ): Post
}
