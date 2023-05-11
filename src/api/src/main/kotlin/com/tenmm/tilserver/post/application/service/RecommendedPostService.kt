package com.tenmm.tilserver.post.application.service

import com.tenmm.tilserver.category.application.inbound.GetCategoryUseCase
import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.common.domain.OperationResult
import com.tenmm.tilserver.post.application.inbound.AddRecommendedPostUseCase
import com.tenmm.tilserver.post.application.inbound.GetPostUseCase
import com.tenmm.tilserver.post.application.inbound.GetRecommendedPostUseCase
import com.tenmm.tilserver.post.application.inbound.model.GetPostListResult
import com.tenmm.tilserver.post.application.outbound.AddRecommendedPostPort
import com.tenmm.tilserver.post.application.outbound.GetRecommendedPostPort
import com.tenmm.tilserver.post.domain.Post
import com.tenmm.tilserver.post.domain.PostDetail
import com.tenmm.tilserver.user.application.inbound.GetUserUseCase
import org.apache.commons.lang3.StringUtils
import org.springframework.stereotype.Service

@Service
class RecommendedPostService(
    private val addRecommendedPostPort: AddRecommendedPostPort,
    private val getRecommendedPostPort: GetRecommendedPostPort,
    private val getPostUseCase: GetPostUseCase,
    private val getCategoryUseCase: GetCategoryUseCase,
    private val getUserUseCase: GetUserUseCase,
) : GetRecommendedPostUseCase, AddRecommendedPostUseCase {
    override fun addByPostId(postIdentifier: Identifier): OperationResult {
        val post = getPostUseCase.getPostByIdentifier(postIdentifier)
        return OperationResult(
            addRecommendedPostPort.addByPostIdentifier(
                categoryIdentifier = post.categoryIdentifier,
                postIdentifier = postIdentifier
            )
        )
    }

    override fun getRecommendedPostListRandom(): GetPostListResult {
        val postIdentifiers = getRecommendedPostPort.getRandom(3)
        val postList = getPostUseCase.getPostListByIdentifiers(postIdentifiers)

        return generatePostWithPath(postList)
    }

    override fun getRecommendedPostListByCategory(categoryIdentifier: Identifier): GetPostListResult {
        val postIdentifiers = getRecommendedPostPort.getByCategoryIdentifier(categoryIdentifier)
        val postList = getPostUseCase.getPostListByIdentifiers(postIdentifiers)

        return generatePostWithPath(postList)
    }

    private fun generatePostWithPath(
        result: List<Post>,
    ): GetPostListResult {
        val userIdentifierPath =
            getUserUseCase.getByIdentifierList(result.map { it.userIdentifier })
        val userIdentifierPathMap = userIdentifierPath.associateBy { it.identifier }
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
}
