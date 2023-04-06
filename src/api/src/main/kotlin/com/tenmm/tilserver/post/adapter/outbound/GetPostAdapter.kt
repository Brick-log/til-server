package com.tenmm.tilserver.post.adapter.outbound

import com.tenmm.tilserver.common.utils.PageTokenConverter
import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.common.domain.NotFoundException
import com.tenmm.tilserver.common.domain.ResultWithToken
import com.tenmm.tilserver.outbound.persistence.repository.PostRepository
import com.tenmm.tilserver.post.application.outbound.GetPostPort
import com.tenmm.tilserver.post.domain.Post
import java.sql.Timestamp
import java.time.LocalDate
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Component

@Component
class GetPostAdapter(
    private val postRepository: PostRepository,
    private val pageTokenConverter: PageTokenConverter,
) : GetPostPort {
    override fun getPostByIdentifier(postIdentifier: Identifier): Post {
        return postRepository.findByIdentifier(postIdentifier.value)?.toModel()
            ?: throw NotFoundException("Not found post - $postIdentifier")
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
        val pageToken = generatePageToken(
            result.size != size + 1,
            categoryIdentifier,
            result[size - 2].id
        ).toString()
        return ResultWithToken(
            data = result.map { it.toModel() },
            pageToken = pageToken
        )
    }

    override fun getPostListByCategoryIdentifierWithPageToken(
        size: Int,
        pageToken: String,
    ): ResultWithToken<List<Post>> {
        val parsedPageToken = pageTokenConverter.decryptPageToken(pageToken, PageTokenSearchPostWithIdentifier::class)
        val result = postRepository.findAllByIdGreaterThanAndCategoryIdentifier(
            id = parsedPageToken.lastEntityId,
            categoryIdentifier = parsedPageToken.identifier.value,
            pageable = generatePageRequest(size + 1)
        )
        val nextPageToken = generatePageToken(
            result.size != size + 1,
            parsedPageToken.identifier,
            result[size - 2].id
        ).toString()

        return ResultWithToken(
            data = result.map { it.toModel() },
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
        val pageToken = generatePageToken(
            result.size != size + 1,
            userIdentifier,
            result[size - 2].id
        ).toString()
        return ResultWithToken(
            data = result.map { it.toModel() },
            pageToken = pageToken
        )
    }

    override fun getPostListByUserAndCreatedAtWithPageToken(
        userIdentifier: Identifier,
        to: Timestamp,
        from: Timestamp,
        size: Int,
        pageToken: String,
    ): ResultWithToken<List<Post>> {
        val parsedPageToken = pageTokenConverter.decryptPageToken(pageToken, PageTokenSearchPostWithIdentifier::class)
        val result =
            postRepository.findAllByIdGreaterThanAndUserIdentifierAndCreatedAtGreaterThanEqualAndCreatedAtLessThan(
                id = parsedPageToken.lastEntityId,
                userIdentifier = parsedPageToken.identifier.value,
                from = from,
                to = to,
                pageable = generatePageRequest(size + 1)
            )

        val nextPageToken = generatePageToken(
            result.size != size + 1,
            parsedPageToken.identifier,
            result[size - 2].id
        ).toString()

        return ResultWithToken(
            data = result.map { it.toModel() },
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
        identifier: Identifier,
        entityId: Long,
    ): String? {
        return if (condition) {
            pageTokenConverter.encryptPageToken(PageTokenSearchPostWithIdentifier(identifier, entityId))
        } else {
            null
        }
    }

    private fun generatePageRequest(size: Int): PageRequest {
        val sort = Sort.by(Sort.Order.desc("created_at"))
        return PageRequest.of(0, size, sort)
    }

    private data class PageTokenSearchPostWithIdentifier(
        val identifier: Identifier,
        val lastEntityId: Long,
    )
}
