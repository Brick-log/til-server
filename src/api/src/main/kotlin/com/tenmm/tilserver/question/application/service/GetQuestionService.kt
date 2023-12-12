package com.tenmm.tilserver.post.application.service

import com.tenmm.tilserver.question.application.outbound.GetQuestionPort
import com.tenmm.tilserver.question.application.inbound.GetQuestionUseCase
import com.tenmm.tilserver.question.adapter.inbound.model.GetQuestionResponse
import com.tenmm.tilserver.question.adapter.inbound.model.Question

import org.springframework.stereotype.Service

@Service
class GetQuestionService(
    private val getQuestionPort: GetQuestionPort,
    private val getQuestionTypeService: GetQuestionTypeService
) : GetQuestionUseCase {
    override fun getQuestionByType(questionType: String): GetQuestionResponse {
        val question = getQuestionPort.findByQuestionType(questionType)
        val questionTypeModel = getQuestionTypeService.getQuestionType(questionType)
        val questions = question.map {
            Question(
                questionName = it.questionName
            )
        }
        return GetQuestionResponse(
            questionType = questionType,
            questionTypeName = questionTypeModel.questionTypeName,
            isRandom = questionTypeModel.isRandom,
            question = questions
        )
    }
}
