package com.tenmm.crawler.post.application.outbound

import com.tenmm.crawler.post.domain.Identifier
import com.tenmm.crawler.post.domain.Post

interface SaveCrawlingPort {
    fun saveCrawling(userIdentifier: Identifier, post: Post): Identifier
}