package com.tenmm.tilserver.post.adapter.outbound

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.common.domain.Url
import com.tenmm.tilserver.post.application.outbound.CrawlingPostPort
import org.springframework.stereotype.Component

@Component
class CrawlingPostAdapter : CrawlingPostPort {
    override fun requestParseFromUrl(url: Url): Identifier {
        TODO("Not yet implemented")
    }
}
