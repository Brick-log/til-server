package com.tenmm.tilserver.crawler.util

import com.tenmm.tilserver.common.domain.Url

object UrlCheck {
    fun getType(url: Url): UrlType {
        return when {
            url.value.contains(UrlType.TISTORY.name.lowercase()) -> UrlType.TISTORY
            url.value.contains(UrlType.NAVER.name.lowercase()) -> UrlType.NAVER
            url.value.contains(UrlType.VELOG.name.lowercase()) -> UrlType.VELOG
            url.value.contains(UrlType.MEDIUM.name.lowercase()) -> UrlType.MEDIUM
            else -> UrlType.ETC
        }
    }
}
