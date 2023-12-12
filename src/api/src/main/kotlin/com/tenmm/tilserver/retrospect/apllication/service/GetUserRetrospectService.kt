package com.tenmm.tilserver.retrospect.application.service

import com.tenmm.tilserver.retrospect.application.inbound.GetUserRetrospectUseCase
import com.tenmm.tilserver.retrospect.adapter.inbound.Model.GetUserRetrospectResponseModel
import com.tenmm.tilserver.retrospect.adapter.inbound.Model.GetRetrospectMetaResponseModel
import com.tenmm.tilserver.retrospect.adapter.inbound.Model.DetailRetrospect
import com.tenmm.tilserver.post.application.service.GetQuestionTypeService
import com.tenmm.tilserver.common.utils.getTimeZone
import org.springframework.stereotype.Service
import com.tenmm.tilserver.common.domain.Identifier
import java.sql.Timestamp
import com.tenmm.tilserver.user.application.inbound.GetUserUseCase
import com.tenmm.tilserver.retrospect.application.outbound.GetUserRetrospectPort

import java.time.format.DateTimeFormatter
import java.time.LocalDate

import com.tenmm.tilserver.retrospect.adapter.inbound.Model.RetrospectQna
import com.tenmm.tilserver.user.application.service.GetUserService

@Service
class GetUserRetrospectService(
    private val getUserService: GetUserService,
    private val getQuestionTypeService: GetQuestionTypeService,
    private val getUserUseCase: GetUserUseCase,
    private val getUserRetrospectPort: GetUserRetrospectPort
) : GetUserRetrospectUseCase {
    override fun getRetrospectListByNameAndDateWithPageToken(
        path: String,
        to: Timestamp,
        from: Timestamp,
        size: Int,
        pageToken: String?,
        isSecret: Boolean,
    ): GetUserRetrospectResponseModel {
        val userInfo = getUserUseCase.getByPath(path)
        if (userInfo.categoryIdentifier == null) {
            return GetUserRetrospectResponseModel(
                retrospects = emptyList(),
                size = 0,
                nextPageToken = ""
            )
        }
        val retrospects = getUserRetrospectPort.getRetrospectListByCreatedAt(
            userIdentifier = userInfo.identifier,
            to = to,
            from = from,
            pageToken = pageToken ?: "",
        )
        val detailRetrospect = retrospects.map {
            DetailRetrospect(
                isSecret = it.isSecret,
                createdAt = it.createdAt,
                questionType = it.questionType,
                questionTypeName = getQuestionTypeService.getQuestionType(it.questionType).questionTypeName,
                userName = getUserService.getByIdentifier(Identifier(it.userIdentifier)).name,
                retrospectIdentifier = if (!it.isSecret || isSecret) it.retrospectIdentifier else "",
                qna = if (!it.isSecret || isSecret) getUserRetrospectPort.getRetrospectListByRetrospectIdentifier(Identifier(it.retrospectIdentifier)).map {
                    RetrospectQna(
                        questionName = it.questionName,
                        answer = it.answer,
                    )
                } else listOf<RetrospectQna>()
            )
        }

        return GetUserRetrospectResponseModel(
            retrospects = detailRetrospect,
            size = retrospects.size,
            nextPageToken = ""
        )
    }

    override fun getRetrospectMetaListByNameAndDate(
        path: String,
        to: Timestamp,
        from: Timestamp,
    ): GetRetrospectMetaResponseModel {
        val userIdentifier = getUserUseCase.getByPath(path).identifier
        val result = getUserRetrospectPort.getRetrospectListByCreatedAt(userIdentifier, to, from, pageToken = "")
        val postMetaTimeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        return GetRetrospectMetaResponseModel(
            metas = result.map {
                LocalDate.ofInstant(it.createdAt.toInstant(), getTimeZone()).format(postMetaTimeFormatter)
            }
        )
    }
}
