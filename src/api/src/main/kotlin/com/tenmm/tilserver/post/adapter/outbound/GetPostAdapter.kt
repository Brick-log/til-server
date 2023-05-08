package com.tenmm.tilserver.post.adapter.outbound

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.common.domain.ResultWithToken
import com.tenmm.tilserver.common.utils.CryptoHandler
import com.tenmm.tilserver.outbound.persistence.repository.PostRepository
import com.tenmm.tilserver.post.application.outbound.GetPostPort
import com.tenmm.tilserver.post.domain.Post
import java.sql.Timestamp
import java.time.Instant
import java.time.LocalDate
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Component

@Component
class GetPostAdapter(
    private val postRepository: PostRepository,
    private val cryptoHandler: CryptoHandler,
) : GetPostPort {
    override fun totalPostCount(userIdentifier: Identifier): Int {
        return postRepository.countAllByUserIdentifier(
            userIdentifier = userIdentifier.value
        )
    }

    override fun getPostByIdentifier(postIdentifier: Identifier): Post? {
        return postRepository.findByIdentifier(postIdentifier.value)?.toModel()
    }

    override fun getPostListByIdentifiers(postIdentifiers: List<Identifier>): List<Post> {
        return postRepository.findAllByIdentifierIn(postIdentifiers.map { it.value }).map { it.toModel() }
    }

    override fun getPostListByCategoryIdentifier(
        categoryIdentifier: Identifier,
        size: Int,
    ): ResultWithToken<List<Post>> {
        val result = postRepository.findAllByCategoryIdentifier(
            categoryIdentifier.value,
            generatePageRequest(size + 1)
        )
        val minOfSize = minOf(size, result.size)
        val resultList = result.subList(0, minOfSize)

        val pageToken = generatePageToken(
            result.size == size + 1,
            categoryIdentifier,
            resultList.lastOrNull()?.id ?: 0
        ).toString()
        return ResultWithToken(
            data = resultList.map { it.toModel() },
            pageToken = pageToken
        )
    }

    override fun getPostListByCategoryIdentifierWithPageToken(
        size: Int,
        pageToken: String,
    ): ResultWithToken<List<Post>> {
        val parsedPageToken = cryptoHandler.decrypt(pageToken, PageTokenSearchPostWithCategoryIdentifier::class)
        val result = postRepository.findAllByIdLessThanAndCategoryIdentifier(
            id = parsedPageToken.lastEntityId,
            categoryIdentifier = parsedPageToken.categoryIdentifier.value,
            pageable = generatePageRequest(size + 1)
        )
        val minOfSize = minOf(size, result.size)
        val resultList = result.subList(0, minOfSize)

        val nextPageToken = generatePageToken(
            result.size == size + 1,
            parsedPageToken.categoryIdentifier,
            resultList.lastOrNull()?.id ?: 0
        ).toString()

        return ResultWithToken(
            data = resultList.map { it.toModel() },
            pageToken = nextPageToken
        )
    }

    override fun getPostListByUserAndCreatedAt(
        userIdentifier: Identifier,
        to: Timestamp,
        from: Timestamp,
        size: Int,
    ): ResultWithToken<List<Post>> {
        val result = postRepository.findAllByUserIdentifierAndCreatedAtGreaterThanEqualAndCreatedAtLessThan(
            userIdentifier.value,
            from,
            to,
            generatePageRequest(size + 1)
        )
        val minOfSize = minOf(size, result.size)
        val resultList = result.subList(0, minOfSize)
        val pageToken = generatePageToken(
            result.size == size + 1,
            userIdentifier,
            from.time,
            to.time,
            resultList.lastOrNull()?.id ?: 0
        ).toString()
        return ResultWithToken(
            data = resultList.map { it.toModel() },
            pageToken = pageToken
        )
    }

    override fun getPostListByUserAndCreatedAtWithPageToken(
        userIdentifier: Identifier,
        size: Int,
        pageToken: String,
    ): ResultWithToken<List<Post>> {
        val parsedPageToken =
            cryptoHandler.decrypt(pageToken, PageTokenSearchPostWithCategoryIdentifierTimePeriod::class)
        val result =
            postRepository.findAllByIdLessThanAndUserIdentifierAndCreatedAtGreaterThanEqualAndCreatedAtLessThan(
                id = parsedPageToken.lastEntityId,
                userIdentifier = parsedPageToken.categoryIdentifier.value,
                from = Timestamp.from(Instant.ofEpochSecond(parsedPageToken.from)),
                to = Timestamp.from(Instant.ofEpochSecond(parsedPageToken.to)),
                pageable = generatePageRequest(size + 1)
            )

        val minOfSize = minOf(size, result.size)
        val resultList = result.subList(0, minOfSize)
        val nextPageToken = generatePageToken(
            result.size == size + 1,
            parsedPageToken.categoryIdentifier,
            parsedPageToken.from,
            parsedPageToken.to,
            resultList.lastOrNull()?.id ?: 0
        ).toString()

        return ResultWithToken(
            data = resultList.map { it.toModel() },
            pageToken = nextPageToken
        )
    }

    override fun getPostListByCreatedAt(userIdentifier: Identifier, to: Timestamp, from: Timestamp): List<Post> {
        return postRepository.findAllByUserIdentifierAndCreatedAtGreaterThanEqualAndCreatedAtLessThan(
            userIdentifier = userIdentifier.value,
            to = to,
            from = from
        ).map { it.toModel() }
    }

    override fun countByUserIdentifierAndMonth(userIdentifier: Identifier, year: Int, month: Int): Int {
        val from = Timestamp.valueOf(LocalDate.of(year, month, 1).atStartOfDay())
        val to = Timestamp.valueOf(LocalDate.of(year, month + 1, 1).atStartOfDay())
        return postRepository.countAllByUserIdentifierAndCreatedAtGreaterThanEqualAndCreatedAtLessThan(
            userIdentifier.value,
            from,
            to
        )
    }

    private fun generatePageToken(
        condition: Boolean,
        categoryIdentifier: Identifier,
        entityId: Long,
    ): String? {
        return if (condition) {
            cryptoHandler.encrypt(PageTokenSearchPostWithCategoryIdentifier(categoryIdentifier, entityId))
        } else {
            null
        }
    }

    private data class PageTokenSearchPostWithCategoryIdentifier(
        val categoryIdentifier: Identifier,
        val lastEntityId: Long,
    )

    private fun generatePageToken(
        condition: Boolean,
        categoryIdentifier: Identifier,
        from: Long,
        to: Long,
        entityId: Long,
    ): String? {
        return if (condition) {
            cryptoHandler.encrypt(
                PageTokenSearchPostWithCategoryIdentifierTimePeriod(
                    categoryIdentifier,
                    from,
                    to,
                    entityId
                )
            )
        } else {
            null
        }
    }

    private data class PageTokenSearchPostWithCategoryIdentifierTimePeriod(
        val categoryIdentifier: Identifier,
        val from: Long,
        val to: Long,
        val lastEntityId: Long,
    )

    private fun generatePageRequest(size: Int): PageRequest {
        val sort = Sort.by(Sort.Order.desc("createdAt"), Sort.Order.desc("id"))
        return PageRequest.of(0, size, sort)
    }
}
