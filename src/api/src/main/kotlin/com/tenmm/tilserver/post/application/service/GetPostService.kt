package com.tenmm.tilserver.post.application.service

import com.tenmm.tilserver.category.application.inbound.GetCategoryUseCase
import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.common.domain.NotFoundException
import com.tenmm.tilserver.post.application.inbound.GetPostUseCase
import com.tenmm.tilserver.post.application.inbound.model.GetPostListResult
import com.tenmm.tilserver.post.application.inbound.model.GetPostMetaResult
import com.tenmm.tilserver.post.application.inbound.model.GetPostResult
import com.tenmm.tilserver.post.application.outbound.GetPostPort
import com.tenmm.tilserver.post.application.outbound.ModifyPostPort
import com.tenmm.tilserver.post.domain.Post
import com.tenmm.tilserver.post.domain.PostDetail
import com.tenmm.tilserver.user.application.inbound.GetUserUseCase
import java.sql.Timestamp
import java.time.LocalDate
import java.time.ZoneId
import org.apache.commons.lang3.StringUtils
import org.springframework.stereotype.Service

@Service
class GetPostService(
    private val getPostPort: GetPostPort,
    private val modifyPostPort: ModifyPostPort,
    private val getCategoryUseCase: GetCategoryUseCase,
    private val getUserUseCase: GetUserUseCase,
) : GetPostUseCase {
    override fun showPostByIdentifier(postIdentifier: Identifier): GetPostResult {
        val post = getPostPort.getPostByIdentifier(postIdentifier) ?: throw NotFoundException("Post not found")
        modifyPostPort.increasePostHitCount(post.identifier)
        return GetPostResult(url = post.url)
    }

    override fun getPostByIdentifier(postIdentifier: Identifier): Post {
        return getPostPort.getPostByIdentifier(postIdentifier) ?: throw NotFoundException("Post not found")
    }

    override fun getPostListByIdentifiers(postIdentifiers: List<Identifier>): List<Post> {
        return getPostPort.getPostListByIdentifiers(postIdentifiers)
    }

    override fun getPostListRandom(size: Int): GetPostListResult {
        val result = getPostPort.getPostRandom(size)

        val userIdentifierPathMap = getUserUseCase.getByIdentifierList(result.map { it.userIdentifier })
            .associateBy { it.identifier }
        val categoryMap = getCategoryUseCase.getAll().associateBy { it.identifier }
        return GetPostListResult(
            posts = result.map {
                PostDetail.generate(
                    post = it,
                    user = userIdentifierPathMap[it.userIdentifier]!!,
                    category = categoryMap[it.categoryIdentifier]!!
                )
            },
            size = result.size,
            nextPageToken = StringUtils.EMPTY
        )
    }

    override fun getPostListByCategory(
        categoryIdentifier: Identifier,
        size: Int,
        pageToken: String?,
    ): GetPostListResult {
        val result = if (pageToken == null) {
            getPostPort.getPostListByCategoryIdentifier(categoryIdentifier, size)
        } else {
            getPostPort.getPostListByCategoryIdentifierWithPageToken(size, pageToken)
        }
        val userIdentifierPathMap = getUserUseCase.getByIdentifierList(result.data.map { it.userIdentifier })
            .associateBy { it.identifier }
        val categoryMap = getCategoryUseCase.getAll().associateBy { it.identifier }
        return GetPostListResult(
            posts = result.data.map {
                PostDetail.generate(
                    post = it,
                    user = userIdentifierPathMap[it.userIdentifier]!!,
                    category = categoryMap[it.categoryIdentifier]!!
                )
            },
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
        val category = getCategoryUseCase.getByIdentifier(userInfo.categoryIdentifier!!)
        val totalCount = getPostPort.totalPostCount(userIdentifier = userInfo.identifier)
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
                posts = it.data.map { post ->
                    PostDetail.generate(
                        post = post,
                        user = userInfo,
                        category = category
                    )
                },
                size = totalCount,
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
