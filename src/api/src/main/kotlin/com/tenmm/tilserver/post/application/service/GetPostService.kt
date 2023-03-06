package com.tenmm.tilserver.post.application.service

import com.tenmm.tilserver.category.application.inbound.GetCategoryUseCase
import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.post.application.inbound.GetPostUseCase
import com.tenmm.tilserver.post.application.inbound.model.GetPostListResult
import com.tenmm.tilserver.post.application.inbound.model.GetPostMetaResult
import com.tenmm.tilserver.post.application.inbound.model.GetPostResult
import com.tenmm.tilserver.post.application.outbound.GetPostPort
import com.tenmm.tilserver.post.domain.Post
import com.tenmm.tilserver.user.application.inbound.GetUserUseCase
import java.sql.Timestamp
import java.time.LocalDate
import java.time.ZoneId
import org.springframework.stereotype.Service

@Service
class GetPostService(
    private val getPostPort: GetPostPort,
    private val getCategoryUseCase: GetCategoryUseCase,
    private val getUserUseCase: GetUserUseCase,
) : GetPostUseCase {
    override fun showPostByIdentifier(postIdentifier: Identifier): GetPostResult {
        return GetPostResult(url = getPostPort.getPostByIdentifier(postIdentifier).url)
    }

    override fun getPostByIdentifier(postIdentifier: Identifier): Post {
        return getPostPort.getPostByIdentifier(postIdentifier)
    }

    override fun getPostListByIdentifiers(postIdentifiers: List<Identifier>): List<Post> {
        return getPostPort.getPostListByIdentifiers(postIdentifiers)
    }

    override fun getPostListRandom(size: Int, pageToken: String?): GetPostListResult {
        return if (pageToken == null) {
            val categoryIdentifier = getCategoryUseCase.getAll().random().identifier
            val result = getPostPort.getPostListByCategoryIdentifier(categoryIdentifier, size)
            GetPostListResult(
                posts = result.data,
                size = result.data.size,
                nextPageToken = result.pageToken
            )
        } else {
            val result = getPostPort.getPostListByCategoryIdentifierWithPageToken(size, pageToken)
            GetPostListResult(
                posts = result.data,
                size = result.data.size,
                nextPageToken = result.pageToken
            )
        }
    }

    override fun getPostListByCategory(
        categoryIdentifier: Identifier,
        size: Int,
        pageToken: String?,
    ): GetPostListResult {
        return if (pageToken == null) {
            val result = getPostPort.getPostListByCategoryIdentifier(categoryIdentifier, size)
            GetPostListResult(
                posts = result.data,
                size = result.data.size,
                nextPageToken = result.pageToken
            )
        } else {
            val result = getPostPort.getPostListByCategoryIdentifierWithPageToken(size, pageToken)
            GetPostListResult(
                posts = result.data,
                size = result.data.size,
                nextPageToken = result.pageToken
            )
        }
    }

    override fun getPostListByNameAndDateWithPageToken(
        path: String,
        to: Timestamp,
        from: Timestamp,
        size: Int,
        pageToken: String?,
    ): GetPostListResult {
        val userIdentifier = getUserUseCase.getByPath(path).identifier
        return if (pageToken == null) {
            getPostPort.getPostListByUserAndCreatedAt(
                userIdentifier,
                to,
                from,
                size
            )
        } else {
            getPostPort.getPostListByUserAndCreatedAtWithPageToken(
                userIdentifier,
                to,
                from,
                size,
                pageToken
            )
        }.let {
            GetPostListResult(
                posts = it.data,
                size = it.data.size,
                nextPageToken = it.pageToken
            )
        }
    }

    override fun getPostMetaListByNameAndDate(
        path: String,
        to: Timestamp,
        from: Timestamp,
    ): GetPostMetaResult {
        val userIdentifier = getUserUseCase.getByPath(path).identifier
        val result = getPostPort.getPostListByCreatedAt(userIdentifier, to, from)
        return GetPostMetaResult(
            dateList = result.map {
                LocalDate.ofInstant(it.createdAt.toInstant(), ZoneId.systemDefault())
            }
        )
    }

    override fun getPostCountByMonth(userIdentifier: Identifier, year: Int, month: Int): Int {
        return getPostPort.countByUserIdentifierAndMonth(userIdentifier, year, month)
    }
}
