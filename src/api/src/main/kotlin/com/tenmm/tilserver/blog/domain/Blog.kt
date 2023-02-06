package com.tenmm.tilserver.blog.domain

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.common.domain.Url
import com.tenmm.tilserver.common.utils.URL_REGEX
import java.lang.IllegalArgumentException

data class Blog(
    val userIdentifier: Identifier,
    val url: Url,
    val platformType: BlogPlatformType,
)

enum class BlogPlatformType(
    val patternRegex: Regex
) {
    TISTORY("^(http|https):\\/\\/.*tistory\\.com.*\$".toRegex()),
    VELOG("^(http|https):\\/\\/.*velog\\.io.*\$".toRegex()),
    NAVER("^(http|https):\\/\\/.*blog\\.naver\\.com.*\$".toRegex()),
    MEDIUM("^(http|https):\\/\\/.*medium\\.com.*\$".toRegex()),
    ETC(URL_REGEX);
    companion object {
        fun get(url: String): BlogPlatformType {
            return when {
                url.matches(VELOG.patternRegex) -> VELOG
                url.matches(TISTORY.patternRegex) -> TISTORY
                url.matches(NAVER.patternRegex) -> NAVER
                url.matches(MEDIUM.patternRegex) -> MEDIUM
                url.matches(ETC.patternRegex) -> ETC
                else -> throw IllegalArgumentException("Invalid URL")
            }
        }
        fun getAll(): List<BlogPlatformType> {
            return BlogPlatformType.values().toList()
        }
    }
}
