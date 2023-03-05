package com.tenmm.tilserver.post.adapter.inbound.rest

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.common.domain.Url
import com.tenmm.tilserver.post.adapter.inbound.rest.model.GetPostListResponse
import com.tenmm.tilserver.post.adapter.inbound.rest.model.GetPostMetaResponse
import com.tenmm.tilserver.post.adapter.inbound.rest.model.GetPostResponse
import com.tenmm.tilserver.post.application.inbound.GetPostUseCase
import com.tenmm.tilserver.post.application.inbound.GetRecommendedPostUseCase
import com.tenmm.tilserver.post.application.inbound.model.GetPostMetaResult
import com.tenmm.tilserver.post.domain.Post
import java.math.BigInteger
import java.sql.Timestamp
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import org.apache.commons.lang3.RandomStringUtils
import org.apache.commons.lang3.RandomUtils
import org.apache.logging.log4j.util.Strings
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/post")
class GetPostController(
    private val getPostUseCase: GetPostUseCase,
    private val getRecommendedPostUseCase: GetRecommendedPostUseCase,
) {
    @GetMapping("/{postIdentifier}")
    fun getPostByIdentifier(
        @PathVariable postIdentifier: Identifier,
    ): GetPostResponse {
//        val post = getPostUseCase.getPostByIdentifier(postIdentifier)
//        return GetPostResponse.fromResult(post)
        return GetPostResponse(Url("https://www.naver.com"))
    }

    @GetMapping("/user/{name}")
    fun getPostByName(
        @PathVariable name: String,
        @RequestParam to: Long,
        @RequestParam from: Long,
        @RequestParam size: Int,
        @RequestParam(required = false) pageToken: String? = null,
    ): GetPostListResponse {
//        val postListResult = if (pageToken != null) {
//            getPostUseCase.getPostListByNameAndDateWithPageToken(
//                name = name,
//                to = Timestamp.from(Instant.ofEpochMilli(to)),
//                from = Timestamp.from(Instant.ofEpochMilli(from)),
//                size = size,
//                pageToken = pageToken
//            )
//        } else {
//            getPostUseCase.getPostListByNameAndDate(
//                name = name,
//                to = Timestamp.from(Instant.ofEpochMilli(to)),
//                from = Timestamp.from(Instant.ofEpochMilli(from)),
//                size = size,
//            )
//        }
//        return GetPostListResponse.fromResult(postListResult)
        return GetPostListResponse(
            postList = generateSequence {
                Post(
                    identifier = Identifier.generate(),
                    userIdentifier = Identifier.generate(),
                    categoryIdentifier = Identifier.generate(),
                    title = RandomStringUtils.random(10),
                    description = RandomStringUtils.random(10),
                    url = Url("https://www.naver.com"),
                    createdAt = Timestamp.from(Instant.now()),
                    hitCount = BigInteger.ONE
                )
            }.take(size).toList(),
            size = size,
            nextPageToken = pageToken ?: Strings.EMPTY
        )
    }

    @GetMapping("/user/{name}/meta")
    fun getPostMetaByName(
        @PathVariable name: String,
        @RequestParam to: Long,
        @RequestParam from: Long,
    ): GetPostMetaResponse {
//        val postMetaResult = getPostUseCase.getPostMetaListByNameAndDate(
//            name = name,
//            to = Timestamp.from(Instant.ofEpochMilli(to)),
//            from = Timestamp.from(Instant.ofEpochMilli(from))
//        )
//        return GetPostMetaResponse.fromResult(postMetaResult)
        val toLocalDate = LocalDate.ofInstant(Instant.ofEpochMilli(to), ZoneId.systemDefault())
        val fromLocalDate = LocalDate.ofInstant(Instant.ofEpochMilli(from), ZoneId.systemDefault())

        var indexLocalDate = toLocalDate
        val resultDateList = emptyList<LocalDate>().toMutableList()
        while (true) {
            if (indexLocalDate == fromLocalDate)
                break
            if (RandomUtils.nextBoolean()) {
                resultDateList.add(indexLocalDate)
            }

            indexLocalDate = indexLocalDate.plusDays(1)
        }

        return GetPostMetaResponse.fromResult(
            GetPostMetaResult(resultDateList)
        )
    }

    @GetMapping("/category")
    fun getByCategory(
        @RequestParam(name = "id", required = false) categoryIdentifier: Identifier? = null,
        @RequestParam size: Int,
        @RequestParam(required = false) pageToken: String? = null,
    ): GetPostListResponse {
//        val postListResult = if (categoryIdentifier != null) {
//            getPostUseCase.getPostListByCategory(categoryIdentifier)
//        } else {
//            getPostUseCase.getPostListRandom()
//        }
//        return GetPostListResponse.fromResult(postListResult)
        return GetPostListResponse(
            postList = generateSequence {
                Post(
                    identifier = Identifier.generate(),
                    userIdentifier = Identifier.generate(),
                    categoryIdentifier = categoryIdentifier ?: Identifier.generate(),
                    title = RandomStringUtils.random(10),
                    description = RandomStringUtils.random(10),
                    url = Url("https://www.naver.com"),
                    createdAt = Timestamp.from(Instant.now()),
                    hitCount = BigInteger.ONE
                )
            }.take(size).toList(),
            size = size,
            nextPageToken = pageToken ?: Strings.EMPTY
        )
    }

    @GetMapping("/category/recommend")
    fun getRecommendationList(
        @RequestParam(name = "id", required = false) categoryIdentifier: Identifier? = null,
    ): GetPostListResponse {
//        val postListResult = if (categoryIdentifier != null) {
//            getRecommendedPostUseCase.getRecommendedPostListByCategory(categoryIdentifier)
//        } else {
//            getRecommendedPostUseCase.getRecommendedPostListRandom()
//        }
//        return GetPostListResponse.fromResult(postListResult)
        return GetPostListResponse(
            postList = generateSequence {
                Post(
                    identifier = Identifier.generate(),
                    userIdentifier = Identifier.generate(),
                    categoryIdentifier = categoryIdentifier ?: Identifier.generate(),
                    title = RandomStringUtils.random(10),
                    description = RandomStringUtils.random(10),
                    url = Url("https://www.naver.com"),
                    createdAt = Timestamp.from(Instant.now()),
                    hitCount = BigInteger.ONE
                )
            }.take(10).toList(),
            size = 10,
            nextPageToken = Strings.EMPTY
        )
    }
}
