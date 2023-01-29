package com.tenmm.tilserver.post.adapter.inbound

import com.tenmm.tilserver.post.adapter.inbound.model.GetPostListResponse
import com.tenmm.tilserver.post.adapter.inbound.model.Post
import java.time.LocalDateTime
import java.time.ZoneOffset
import org.apache.commons.lang3.RandomStringUtils
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/posts")
class GetPostController {
    @GetMapping("/user/{name}")
    fun getPostByUser(
        @PathVariable name: String,
    ): GetPostListResponse {
        return GetPostListResponse(
            postList = listOf(
                Post(
                    id = RandomStringUtils.random(10),
                    title = RandomStringUtils.random(10),
                    summary = RandomStringUtils.random(10),
                    author = RandomStringUtils.random(10),
                    url = RandomStringUtils.random(10),
                    createdAt = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)
                )
            ),
            nextPageToken = "user - $name next token",
            prevPageToken = "user - $name prev token",
        )
    }

    @GetMapping("/recommendation")
    fun getRecommendationList(): GetPostListResponse {
        return GetPostListResponse(
            postList = listOf(
                Post(
                    id = RandomStringUtils.random(10),
                    title = RandomStringUtils.random(10),
                    summary = RandomStringUtils.random(10),
                    author = RandomStringUtils.random(10),
                    url = RandomStringUtils.random(10),
                    createdAt = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)
                )
            ),
            nextPageToken = "recommend next token",
            prevPageToken = "recommend prev token",
        )
    }

    @GetMapping("/{categoryId}")
    fun getByCategory(
        @PathVariable categoryId: String,
    ): GetPostListResponse {
        return GetPostListResponse(
            postList = listOf(
                Post(
                    id = RandomStringUtils.random(10),
                    title = RandomStringUtils.random(10),
                    summary = RandomStringUtils.random(10),
                    author = RandomStringUtils.random(10),
                    url = RandomStringUtils.random(10),
                    createdAt = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)
                )
            ),
            nextPageToken = "category - $categoryId next token",
            prevPageToken = "category - $categoryId prev token",
        )
    }
}
