package com.tenmm.tilserver.post.application.service

import com.tenmm.tilserver.question.application.outbound.GetQuestionTypePort
import com.tenmm.tilserver.question.application.inbound.GetQuestionTypeUseCase
import com.tenmm.tilserver.question.adapter.inbound.model.GetQuestionTypeResponse
import com.tenmm.tilserver.question.adapter.inbound.model.QuestionType

import org.springframework.stereotype.Service

@Service
class GetQuestionTypeService(
    private val getQuestionTypePort: GetQuestionTypePort,
) : GetQuestionTypeUseCase {
    override fun getQuestionTypeList(): GetQuestionTypeResponse {
        val questionType = getQuestionTypePort.findAll()
        val questionTypes = questionType.map {
            QuestionType(
                type = it.type,
                name = it.name
            )
        }
        return GetQuestionTypeResponse(
            types = questionTypes
        )
    }
}
