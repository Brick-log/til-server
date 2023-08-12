package com.tenmm.tilserver.post.application.service

import com.tenmm.tilserver.category.application.inbound.GetCategoryUseCase
import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.common.domain.PostNotFoundException
import com.tenmm.tilserver.common.utils.getTimeZone
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
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.supervisorScope
import org.springframework.stereotype.Service

@Service
class GetPostService(
    private val getPostPort: GetPostPort,
    private val modifyPostPort: ModifyPostPort,
    private val getCategoryUseCase: GetCategoryUseCase,
    private val getUserUseCase: GetUserUseCase,
) : GetPostUseCase {
    override fun showPostByIdentifier(postIdentifier: Identifier): GetPostResult {
        val post = getPostPort.getPostByIdentifier(postIdentifier) ?: throw PostNotFoundException()
        modifyPostPort.increasePostHitCount(post.identifier)
        return GetPostResult(url = post.url)
    }

    override fun getPostByIdentifier(postIdentifier: Identifier): Post {
        return getPostPort.getPostByIdentifier(postIdentifier) ?: throw PostNotFoundException()
    }

    override fun getPostListByIdentifiers(postIdentifiers: List<Identifier>): List<Post> {
        return getPostPort.getPostListByIdentifiers(postIdentifiers)
    }

    override fun getPostListByNameAndDateWithPageToken(
        path: String,
        to: Timestamp,
        from: Timestamp,
        size: Int,
        pageToken: String?,
    ): GetPostListResult {
        val userInfo = getUserUseCase.getByPath(path)
        if (userInfo.categoryIdentifier == null) {
            return GetPostListResult(
                posts = emptyList(),
                size = 0,
                nextPageToken = null
            )
        }
        val category = getCategoryUseCase.getByIdentifier(userInfo.categoryIdentifier!!)
        val totalCount = getPostPort.totalPostCountByUser(userIdentifier = userInfo.identifier)
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
                LocalDate.ofInstant(it.createdAt.toInstant(), getTimeZone())
            }
        )
    }

    override fun getPostCountByMonth(userIdentifier: Identifier, year: Int, month: Int): Int {
        return getPostPort.countByUserIdentifierAndMonth(userIdentifier, year, month)
    }

    override suspend fun getPostListByCategory(categoryIdentifier: String, size: Int): GetPostListResult {
        val posts = getPostPort.getPostListByCategoryIdentifier(
            categoryIdentifier = categoryIdentifier,
            size = size
        )
        val (categories, users) = getCategoryToUserMap(categoryIdentifier, posts.data)

        return GetPostListResult(
            posts = posts.data.map { post ->
                PostDetail.generate(
                    post = post,
                    user = users[post.userIdentifier]!!,
                    category = categories[post.categoryIdentifier]!!
                )
            },
            size = posts.data.size,
            nextPageToken = posts.pageToken
        )
    }

    override suspend fun getPostListWithPageToken(pageToken: String, size: Int): GetPostListResult {
        val posts = getPostPort.getPostListWithPageToken(
            pageToken = pageToken,
            size = size
        )

        val (categories, users) = getCategoryToUserMap(posts.data.first().categoryIdentifier, posts.data)

        return GetPostListResult(
            posts = posts.data.map { post ->
                PostDetail.generate(
                    post = post,
                    user = users[post.userIdentifier]!!,
                    category = categories[post.categoryIdentifier]!!
                )
            },
            size = posts.data.size,
            nextPageToken = posts.pageToken
        )
    }

    private suspend fun getCategoryToUserMap(categoryIdentifier: String, posts: List<Post>) = supervisorScope {
        val categoryDeferred = async {
            if (categoryIdentifier == "all")
                getCategoryUseCase.getAll()
            else
                listOf(getCategoryUseCase.getByIdentifier(categoryIdentifier))
        }
        val userDeferred = posts.map { post ->
            async {
                getUserUseCase.getByIdentifier(post.userIdentifier)
            }
        }

        categoryDeferred.await().associateBy { it.identifier } to userDeferred.awaitAll().associateBy { it.identifier }
    }
}
