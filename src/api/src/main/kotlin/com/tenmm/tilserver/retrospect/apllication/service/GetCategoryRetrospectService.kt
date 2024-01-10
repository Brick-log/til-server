package com.tenmm.tilserver.retrospect.application.service

import com.tenmm.tilserver.category.application.inbound.GetCategoryUseCase
import com.tenmm.tilserver.retrospect.application.inbound.GetCategoryRetrospectUseCase
import com.tenmm.tilserver.retrospect.adapter.inbound.Model.GetUserRetrospectResponseModel
import com.tenmm.tilserver.retrospect.application.outbound.GetCategoryRetrospectPort
import com.tenmm.tilserver.retrospect.application.outbound.GetUserRetrospectPort
import com.tenmm.tilserver.retrospect.adapter.inbound.Model.DetailRetrospect
import com.tenmm.tilserver.retrospect.adapter.inbound.Model.RetrospectQna
import com.tenmm.tilserver.post.application.service.GetQuestionTypeService

import com.tenmm.tilserver.retrospect.adapter.outbound.model.RetrospectList
import com.tenmm.tilserver.user.application.service.GetUserService

import org.springframework.stereotype.Service
import com.tenmm.tilserver.common.domain.Identifier

@Service
class GetCategoryRetrospectService(
    private val getUserService: GetUserService,
    private val getQuestionTypeService: GetQuestionTypeService,
    private val getCategoryRetrospectPort: GetCategoryRetrospectPort,
    private val getUserRetrospectPort: GetUserRetrospectPort,
    private val getCategoryUseCase: GetCategoryUseCase,
) : GetCategoryRetrospectUseCase {
    override fun getRetrospectListByCategoryIdentifierWithPageToken(pageToken: String, categoryIdentifier: String, size: Int, userIdentifier: Identifier?): GetUserRetrospectResponseModel {
        val retrospectList: RetrospectList = getCategoryRetrospectPort.getRetrospectListByCategoryIdentifierWithPageToken(pageToken, categoryIdentifier, size)
        return generateRetrospectWithPath(retrospectList, userIdentifier)
    }

    override fun getRetrospectListByCategoryIdentifier(categoryIdentifier: String, size: Int, userIdentifier: Identifier?): GetUserRetrospectResponseModel {
        val retrospectList: RetrospectList = getCategoryRetrospectPort.getRetrospectListByCategoryIdentifier(categoryIdentifier, size)
        return generateRetrospectWithPath(retrospectList, userIdentifier)
    }

    override fun getRecommendedRetrospectListRandom(): GetUserRetrospectResponseModel {
        val retrospectIdentifiers = getCategoryRetrospectPort.getRandom(3)
        val retrospectList = getCategoryRetrospectPort.getRetrospectListByIdentifiers(retrospectIdentifiers)

        return generateRetrospectWithPath(retrospectList, null)
    }

    override fun getRecommendedRetrospectListByCategory(categoryIdentifier: String): GetUserRetrospectResponseModel {
        val retrospectIdentifiers = getCategoryRetrospectPort.getByCategoryIdentifier(categoryIdentifier)
        val retrospectList = getCategoryRetrospectPort.getRetrospectListByIdentifiers(retrospectIdentifiers)

        return generateRetrospectWithPath(retrospectList, null)
    }

    private fun generateRetrospectWithPath(
        retrospectList: RetrospectList,
        userIdentifier: Identifier?
    ): GetUserRetrospectResponseModel {
        val categoryMap = getCategoryUseCase.getAll().associateBy { it.identifier }
        return GetUserRetrospectResponseModel(
            size = retrospectList.retrospectList.size,
            nextPageToken = retrospectList.nextPageToken,
            retrospects = retrospectList.retrospectList.map {
                val user = getUserService.getByIdentifier(Identifier(it.userIdentifier))
                DetailRetrospect(
                    isSecret = it.isSecret,
                    createdAt = it.createdAt,
                    questionType = it.questionType,
                    questionTypeName = getQuestionTypeService.getQuestionType(it.questionType).questionTypeName,
                    userName = user.name,
                    userPath = user.path,
                    profileImgSrc = user.thumbnailUrl.value,
                    categoryIdentifier = it.categoryIdentifier,
                    categoryName = categoryMap[it.categoryIdentifier]!!.name,
                    retrospectIdentifier = if (!it.isSecret || (userIdentifier != null && it.userIdentifier == userIdentifier.value)) it.retrospectIdentifier else "",
                    qna = if (!it.isSecret || (userIdentifier != null && it.userIdentifier == userIdentifier.value)) getUserRetrospectPort.getRetrospectListByRetrospectIdentifier(Identifier(it.retrospectIdentifier)).map {
                        RetrospectQna(
                            questionName = it.questionName,
                            answer = it.answer,
                        )
                    } else listOf<RetrospectQna>()
                )
            }
        )
    }
}
