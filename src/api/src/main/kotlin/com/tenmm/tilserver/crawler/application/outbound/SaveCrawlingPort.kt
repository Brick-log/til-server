package com.tenmm.tilserver.crawler.application.outbound

import com.tenmm.tilserver.crawler.domain.Identifier
import com.tenmm.tilserver.crawler.domain.Post

interface SaveCrawlingPort {
    suspend fun saveCrawling(userIdentifier: Identifier, post: Post): Identifier
}
