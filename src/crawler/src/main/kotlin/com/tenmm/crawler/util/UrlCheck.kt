package com.tenmm.crawler.util

class UrlCheck {
    companion object {
        fun getDomain(url: String): String {
            if (url.contains(UrlType.TISTORY.name)) {
                return UrlType.TISTORY.name
            } else if (url.contains(UrlType.NAVER.name)) {
                return UrlType.NAVER.name
            } else if (url.contains(UrlType.VELOG.name)) {
                return UrlType.VELOG.name
            } else if (url.contains(UrlType.MEDIUM.name)) {
                return UrlType.MEDIUM.name
            } else {
                return UrlType.ETC.name
            }
        }
    }
}
