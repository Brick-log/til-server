package com.tenmm.tilserver.post.application.service

import com.tenmm.tilserver.category.application.inbound.GetCategoryUseCase
import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.common.domain.NotFoundException
import com.tenmm.tilserver.common.domain.ResultWithToken
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
        val post = getPostPort.getPostByIdentifier(postIdentifier) ?: throw NotFoundException("Post not found")
        return GetPostResult(url = post.url)
    }

    override fun getPostByIdentifier(postIdentifier: Identifier): Post {
        return getPostPort.getPostByIdentifier(postIdentifier) ?: throw NotFoundException("Post not found")
    }

    override fun getPostListByIdentifiers(postIdentifiers: List<Identifier>): List<Post> {
        return getPostPort.getPostListByIdentifiers(postIdentifiers)
    }

    override fun getPostListRandom(size: Int, pageToken: String?): GetPostListResult {
        val categoryIdentifier = getCategoryUseCase.getAll().random().identifier
        return getPostListByCategoryIdentifier(categoryIdentifier, size, pageToken)
    }

    override fun getPostListByCategory(
        categoryIdentifier: Identifier,
        size: Int,
        pageToken: String?,
    ): GetPostListResult {
        return getPostListByCategoryIdentifier(categoryIdentifier, size, pageToken)
    }

    private fun getPostListByCategoryIdentifier(
        categoryIdentifier: Identifier,
        size: Int,
        pageToken: String?,
    ): GetPostListResult {
        val result = if (pageToken == null) {
            getPostPort.getPostListByCategoryIdentifier(categoryIdentifier, size)
        } else {
            getPostPort.getPostListByCategoryIdentifierWithPageToken(size, pageToken)
        }
        return generatePostWithPath(result)
    }

    private fun generatePostWithPath(
        result: ResultWithToken<List<Post>>,
    ): GetPostListResult {
        val userIdentifierPathMap =
            getUserUseCase.getByIdentifierList(result.data.map { it.userIdentifier }).associateBy { it.identifier }
        return GetPostListResult(
            posts = result.data.map { it.setUserInfo(userIdentifierPathMap[it.userIdentifier]!!) },
            size = result.data.size,
            nextPageToken = result.pageToken
        )
    }

    override fun getPostListByNameAndDateWithPageToken(
        path: String,
        to: Timestamp,
        from: Timestamp,
        size: Int,
        pageToken: String?,
    ): GetPostListResult {
        val userInfo = getUserUseCase.getByPath(path)
        return if (pageToken == null) {
            getPostPort.getPostListByUserAndCreatedAt(
                userInfo.identifier,
                to,
                from,
                size
            )
        } else {
            getPostPort.getPostListByUserAndCreatedAtWithPageToken(
                userInfo.identifier,
                size,
                pageToken
            )
        }.let {
            GetPostListResult(
                posts = it.data.map { it.setUserInfo(userInfo) },
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
