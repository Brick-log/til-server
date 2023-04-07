package com.tenmm.crawler.post.application.outbound

import com.tenmm.crawler.post.domain.CssSelectorInfo
import com.tenmm.crawler.post.domain.Post
import com.tenmm.crawler.post.domain.Url

interface DoCrawlingPort {
    fun crawling(
        url: Url,
        cssSelectorInfo: CssSelectorInfo,
    ): Post
}
