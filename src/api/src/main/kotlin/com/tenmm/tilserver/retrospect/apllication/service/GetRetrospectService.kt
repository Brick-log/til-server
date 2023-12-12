package com.tenmm.tilserver.retrospect.application.service

import com.tenmm.tilserver.retrospect.application.inbound.GetRetrospectUseCase
import com.tenmm.tilserver.retrospect.application.outbound.GetRetrospectPort
import com.tenmm.tilserver.retrospect.adapter.inbound.Model.GetRetrospectResponseModel
import com.tenmm.tilserver.retrospect.adapter.inbound.Model.SimpleRetrospect
import com.tenmm.tilserver.post.application.service.GetQuestionTypeService
import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.retrospect.application.inbound.Model.RetrospectDto

import org.springframework.stereotype.Service

@Service
class GetRetrospectService(
    private val getQuestionTypeService: GetQuestionTypeService,
    private val getRetrospectPort: GetRetrospectPort
) : GetRetrospectUseCase {
    override fun getRetrospect(userIdentifier: Identifier): List<GetRetrospectResponseModel> {
        return getRetrospectPort.findOneRetrospectByUserIdentifierToday(userIdentifier).map {
            GetRetrospectResponseModel(
                questionType = it.questionType,
                questionTypeName = getQuestionTypeService.getQuestionType(it.questionType).questionTypeName,
                retrospectIdentifier = it.retrospectIdentifier,
                retrospect = getRetrospectPort.findRetrospectQnaListByRetrospectIdentifier(Identifier(it.retrospectIdentifier)).map {
                    SimpleRetrospect(
                        questionName = it.questionName,
                        answer = it.answer,
                    )
                }
            )
        }
    }

    override fun getRetrospectById(retrospectIdentifier: Identifier): RetrospectDto {
        val retrospect = getRetrospectPort.findOneRetrospectById(retrospectIdentifier)
        return RetrospectDto(
            questionType = retrospect?.questionType,
            retrospectIdentifier = retrospect?.retrospectIdentifier,
            categoryIdentifier = retrospect?.categoryIdentifier,
            userIdentifier = retrospect?.userIdentifier,
            isSecret = retrospect?.isSecret,
            createdAt = retrospect?.createdAt
        )   
    }
}
