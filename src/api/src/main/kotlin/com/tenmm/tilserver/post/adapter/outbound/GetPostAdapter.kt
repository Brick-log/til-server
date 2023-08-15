package com.tenmm.tilserver.post.adapter.outbound

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.common.domain.ResultWithToken
import com.tenmm.tilserver.common.utils.CryptoHandler
import com.tenmm.tilserver.outbound.persistence.entity.PostEntity
import com.tenmm.tilserver.outbound.persistence.repository.PostRepository
import com.tenmm.tilserver.post.application.outbound.GetPostPort
import com.tenmm.tilserver.post.domain.Post
import java.sql.Timestamp
import java.time.Instant
import java.time.LocalDate
import org.springframework.stereotype.Component

@Component
class GetPostAdapter(
    private val postRepository: PostRepository,
    private val cryptoHandler: CryptoHandler,
) : GetPostPort {
    override fun totalPostCountByUser(userIdentifier: Identifier): Int {
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
        categoryIdentifier: String,
        size: Int,
    ): ResultWithToken<List<Post>> {
        val result = if (categoryIdentifier == "all") {
            postRepository.findAll(size + 1)
        } else {
            postRepository.findAllByCategoryIdentifier(
                categoryIdentifier,
                size + 1
            )
        }

        val resultList = result.subList(0, minOf(size, result.size))

        val pageToken = resultList.lastOrNull()?.let { lastEntity ->
            generatePageToken(
                condition = result.size == size + 1,
                categoryIdentifier = categoryIdentifier,
                lastEntity = lastEntity
            )
        }

        return ResultWithToken(
            data = resultList.map { it.toModel() },
            pageToken = pageToken
        )
    }

    override fun getPostListByCategoryIdentifierWithPageToken(
        pageToken: String,
        categoryIdentifier: String,
        size: Int,
    ): ResultWithToken<List<Post>> {
        val parsedPageToken = cryptoHandler.decrypt(pageToken, PageTokenSearchPostWithCategoryIdentifier::class)
        val result = if (categoryIdentifier == "all") {
            postRepository.findAllByCreatedAtBefore(
                lastEntityId = parsedPageToken.lastEntityId,
                lastEntityCreatedAt = parsedPageToken.lastEntityCreatedAt,
                size = size + 1
            )
        } else {
            postRepository.findAllByCreatedAtBeforeAndCategoryIdentifier(
                lastEntityId = parsedPageToken.lastEntityId,
                lastEntityCreatedAt = parsedPageToken.lastEntityCreatedAt,
                categoryIdentifier = categoryIdentifier,
                size = size + 1
            )
        }

        val resultList = result.subList(0, minOf(size, result.size))

        val nextPageToken = resultList.lastOrNull()?.let { lastEntity ->
            generatePageToken(
                condition = result.size == size + 1,
                categoryIdentifier = parsedPageToken.categoryIdentifier,
                lastEntity = lastEntity
            )
        }
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
        val result = postRepository.findByPostListByUserIdentifierAndTimePeriodWithSize(
            userIdentifier.value,
            from,
            to,
            size + 1
        )
        val minOfSize = minOf(size, result.size)
        val resultList = result.subList(0, minOfSize)
        val pageToken = generatePageToken(
            result.size == size + 1,
            userIdentifier.value,
            from.time / 1000,
            to.time / 1000,
            resultList.lastOrNull()
        )
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
            postRepository.findByPostListByUserIdentifierAndTimePeriodAndWithSizeUsingPageToken(
                lastEntityId = parsedPageToken.lastEntityId,
                lastEntityCreatedAt = parsedPageToken.lastEntityCreatedAt,
                userIdentifier = userIdentifier.value,
                from = Timestamp.from(Instant.ofEpochSecond(parsedPageToken.from)),
                to = Timestamp.from(Instant.ofEpochSecond(parsedPageToken.to)),
                size = size + 1
            )

        val minOfSize = minOf(size, result.size)
        val resultList = result.subList(0, minOfSize)
        val nextPageToken = generatePageToken(
            result.size == size + 1,
            parsedPageToken.categoryIdentifier,
            parsedPageToken.from,
            parsedPageToken.to,
            resultList.lastOrNull()
        )

        return ResultWithToken(
            data = resultList.map { it.toModel() },
            pageToken = nextPageToken
        )
    }

    override fun getPostListByCreatedAt(userIdentifier: Identifier, to: Timestamp, from: Timestamp): List<Post> {
        return postRepository.findByPostListByUserIdentifierAndTimePeriod(
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
        categoryIdentifier: String,
        lastEntity: PostEntity?,
    ): String? {
        return if (condition) {
            lastEntity!!
            cryptoHandler.encrypt(
                PageTokenSearchPostWithCategoryIdentifier(
                    categoryIdentifier,
                    lastEntity.id.toInt(),
                    lastEntity.createdAt
                )
            )
        } else {
            null
        }
    }

    private data class PageTokenSearchPostWithCategoryIdentifier(
        val categoryIdentifier: String,
        val lastEntityId: Int,
        val lastEntityCreatedAt: Timestamp,
    )

    private fun generatePageToken(
        condition: Boolean,
        categoryIdentifier: String,
        from: Long,
        to: Long,
        lastEntity: PostEntity?,
    ): String? {
        return if (condition) {
            lastEntity!!
            cryptoHandler.encrypt(
                PageTokenSearchPostWithCategoryIdentifierTimePeriod(
                    categoryIdentifier,
                    from,
                    to,
                    lastEntity.id.toInt(),
                    lastEntity.createdAt
                )
            )
        } else {
            null
        }
    }

    private data class PageTokenSearchPostWithCategoryIdentifierTimePeriod(
        val categoryIdentifier: String,
        val from: Long,
        val to: Long,
        val lastEntityId: Int,
        val lastEntityCreatedAt: Timestamp,
    )
}
