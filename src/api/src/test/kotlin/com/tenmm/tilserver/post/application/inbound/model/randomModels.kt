package com.tenmm.tilserver.post.application.inbound.model

import com.tenmm.tilserver.common.domain.Url
import com.tenmm.tilserver.common.randomUrl

fun randomGetPostResult(
    url:Url = randomUrl()
): GetPostResult {
    return GetPostResult(url)
}
