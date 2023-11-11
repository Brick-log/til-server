package com.tenmm.tilserver.retrospect.application.service

import com.tenmm.tilserver.retrospect.application.inbound.GetCategoryRetrospectUseCase
import com.tenmm.tilserver.retrospect.adapter.inbound.Model.GetUserRetrospectResponseModel
import com.tenmm.tilserver.retrospect.application.outbound.GetCategoryRetrospectPort
import com.tenmm.tilserver.retrospect.application.outbound.GetUserRetrospectPort
import com.tenmm.tilserver.retrospect.adapter.inbound.Model.DetailRetrospect
import com.tenmm.tilserver.retrospect.adapter.inbound.Model.RetrospectQna

import com.tenmm.tilserver.retrospect.adapter.outbound.model.RetrospectListWithPageToken
import com.tenmm.tilserver.retrospect.adapter.outbound.model.RetrospectList

import org.springframework.stereotype.Service
import com.tenmm.tilserver.common.domain.Identifier

@Service
class GetCategoryRetrospectService(
    private val getCategoryRetrospectPort: GetCategoryRetrospectPort,
    private val getUserRetrospectPort: GetUserRetrospectPort
) : GetCategoryRetrospectUseCase {
    override fun getRetrospectListByTypeWithPageToken(pageToken: String, retrospectType: String, size: Int, userIdentifier: Identifier?): GetUserRetrospectResponseModel {
        val retrospectListWithPageToken: RetrospectListWithPageToken = getCategoryRetrospectPort.getRetrospectListByTypeWithPageToken(pageToken, retrospectType, size)
        val getUserRetrospectResponseModel: GetUserRetrospectResponseModel = GetUserRetrospectResponseModel(
            size = retrospectListWithPageToken.retrospectList.size,
            nextPageToken = retrospectListWithPageToken.nextPageToken ?: "",
            retrospects = retrospectListWithPageToken.retrospectList.map {
                DetailRetrospect(
                    isSecret = it.isSecret,
                    createdAt = it.createdAt,
                    id = if (!it.isSecret || (userIdentifier != null && it.userIdentifier == userIdentifier.value)) it.retrospectIdentifier else "",
                    qna = if (!it.isSecret || (userIdentifier != null && it.userIdentifier == userIdentifier.value)) getUserRetrospectPort.getRetrospectListByRetrospectIdentifier(Identifier(it.retrospectIdentifier)).map {
                        RetrospectQna(
                            question = it.question,
                            answer = it.answer,
                        )
                    } else listOf<RetrospectQna>()
                )
            }
        )
        return getUserRetrospectResponseModel
    }

    override fun getRetrospectListByType(retrospectType: String, size: Int, userIdentifier: Identifier?): GetUserRetrospectResponseModel {
        val retrospectListWithPageToken: RetrospectList = getCategoryRetrospectPort.getRetrospectListByType(retrospectType, size)
        val getUserRetrospectResponseModel: GetUserRetrospectResponseModel = GetUserRetrospectResponseModel(
            size = retrospectListWithPageToken.retrospectList.size,
            nextPageToken = "",
            retrospects = retrospectListWithPageToken.retrospectList.map {
                DetailRetrospect(
                    isSecret = it.isSecret,
                    createdAt = it.createdAt,
                    id = if (!it.isSecret || (userIdentifier != null && it.userIdentifier == userIdentifier.value)) it.retrospectIdentifier else "",
                    qna = if (!it.isSecret || (userIdentifier != null && it.userIdentifier == userIdentifier.value)) getUserRetrospectPort.getRetrospectListByRetrospectIdentifier(Identifier(it.retrospectIdentifier)).map {
                        RetrospectQna(
                            question = it.question,
                            answer = it.answer,
                        )
                    } else listOf<RetrospectQna>()
                )
            }
        )
        return getUserRetrospectResponseModel
    }
}
