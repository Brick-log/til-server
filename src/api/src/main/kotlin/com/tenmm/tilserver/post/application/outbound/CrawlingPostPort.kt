package com.tenmm.tilserver.post.application.outbound

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.common.domain.Url

interface CrawlingPostPort {
    fun requestParseFromUrl(url: Url): Identifier
}
