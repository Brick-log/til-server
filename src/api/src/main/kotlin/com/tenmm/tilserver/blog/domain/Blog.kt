package com.tenmm.tilserver.blog.domain

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.common.domain.Url

data class Blog(
    val userIdentifier: Identifier,
    val url: Url,
    val platformType: BlogPlatformType,
)

enum class BlogPlatformType {
    Tistory,
    Velog,
    Naver,
    Medium,
    ETC
}
