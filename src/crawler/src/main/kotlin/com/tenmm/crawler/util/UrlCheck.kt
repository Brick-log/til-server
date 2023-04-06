package com.tenmm.crawler.util

class UrlCheck {
    companion object {
        fun getDomain(url: String): String {
            if (url.contains(UrlType.TISTORY.name.lowercase())) {
                return UrlType.TISTORY.name
            } else if (url.contains(UrlType.NAVER.name.lowercase())) {
                return UrlType.NAVER.name
            } else if (url.contains(UrlType.VELOG.name.lowercase())) {
                return UrlType.VELOG.name
            } else if (url.contains(UrlType.MEDIUM.name.lowercase())) {
                return UrlType.MEDIUM.name
            } else {
                return UrlType.ETC.name
            }
        }
    }
}
