package com.tenmm.tilserver.post.adapter.inbound

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.common.domain.Url
import com.tenmm.tilserver.post.adapter.inbound.model.GetPostListResponse
import com.tenmm.tilserver.post.domain.Post
import java.math.BigInteger
import java.sql.Timestamp
import java.time.Instant
import java.util.UUID
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
                    identifier = Identifier.generate(),
                    userIdentifier = Identifier(UUID.randomUUID().toString()),
                    title = RandomStringUtils.random(10),
                    description = RandomStringUtils.random(10),
                    categoryIdentifier = Identifier(UUID.randomUUID().toString()),
                    url = Url(RandomStringUtils.random(10)),
                    createdAt = Timestamp.from(Instant.now()),
                    hitCount = BigInteger.ONE,
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
                    identifier = Identifier.generate(),
                    userIdentifier = Identifier(UUID.randomUUID().toString()),
                    title = RandomStringUtils.random(10),
                    description = RandomStringUtils.random(10),
                    categoryIdentifier = Identifier(UUID.randomUUID().toString()),
                    url = Url(RandomStringUtils.random(10)),
                    createdAt = Timestamp.from(Instant.now()),
                    hitCount = BigInteger.ONE,
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
                    identifier = Identifier.generate(),
                    userIdentifier = Identifier(UUID.randomUUID().toString()),
                    title = RandomStringUtils.random(10),
                    description = RandomStringUtils.random(10),
                    categoryIdentifier = Identifier(UUID.randomUUID().toString()),
                    url = Url(RandomStringUtils.random(10)),
                    createdAt = Timestamp.from(Instant.now()),
                    hitCount = BigInteger.ONE,
                )
            ),
            nextPageToken = "category - $categoryId next token",
            prevPageToken = "category - $categoryId prev token",
        )
    }
}
