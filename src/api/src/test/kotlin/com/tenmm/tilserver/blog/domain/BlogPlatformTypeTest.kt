package com.tenmm.tilserver.blog.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/**
 * Velog의 경우, velog.io/@username/title 의 형태를 가진다.
 */
class TestVelog {
    @Test
    fun `Test Velog (Standard)`() {
        val url = "https://velog.io/@username/post"
        assertEquals(BlogPlatformType.VELOG, BlogPlatformType.get(url))
    }
}

/**
 * Medium의 경우, medium.com이 다양한 곳에 위치 할 수 있다.
 *
 * Case 1: medium.com/@username/title
 * Case 2: [prefix].meduim.com/title
 * Case 3: medium.com/[postfix]/title
 * Case 4: medium.com이 아예 들어가지 않음 (식별 불가)
 */
class TestMeduim {
    @Test
    fun `Test Meduim (Standard)`() {
        val url = "https://medium.com/@username/title"
        assertEquals(BlogPlatformType.MEDIUM, BlogPlatformType.get(url))
    }

    @Test
    fun `Test Meduim (with prefix)`() {
        val url = "https://jacquelinedooley.medium.com/title"
        assertEquals(BlogPlatformType.MEDIUM, BlogPlatformType.get(url))
    }

    @Test
    fun `Test Meduim (with postfix)`() {
        val url = "https://medium.com/postfix/title"
        assertEquals(BlogPlatformType.MEDIUM, BlogPlatformType.get(url))
    }
    @Test
    fun `Test Meduim (not contain meduim URL)`() {
        /**
         * Sample Real URL
         */
        val url = "https://betterprogramming.pub/context-over-task-lists-2e8912d7df61"

        assertEquals(BlogPlatformType.ETC, BlogPlatformType.get(url))
    }
}

/**
 * Naver 의 경우, blog.naver.com으로 시작한다.
 * blog.naver.com/nickname/[postIdentifier]
 *
 */
class TestNaver {
    @Test
    fun `Test naver (Standard)`() {
        val url = "https://blog.naver.com/nickname/223302012"
        assertEquals(BlogPlatformType.NAVER, BlogPlatformType.get(url))
    }
}

/**
 * Tistory 의 경우, 다양한 형태를 가진다.
 * Case 1: [prefix]].tistory.com/[postIdentifier]의 형태를 가진다.
 * Case 2: tistory.com이 아예 들어가지 않음 (식별 불가)
 */
class TestTisotry {
    @Test
    fun `Test Tistory (Standard)`() {
        val url = "https://prefix.tistory.com/nickname/223302012"
        assertEquals(BlogPlatformType.TISTORY, BlogPlatformType.get(url))
    }

    @Test
    fun `Test Tistory (not contain tistory URL)`() {
        /**
         * Sample Real URL
         */
        val url = "https://blog.lgcns.com/"
        assertEquals(BlogPlatformType.ETC, BlogPlatformType.get(url))
    }
}
