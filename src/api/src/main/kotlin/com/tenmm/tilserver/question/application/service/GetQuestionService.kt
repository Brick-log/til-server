package com.tenmm.tilserver.post.application.service

import com.tenmm.tilserver.question.application.outbound.GetQuestionPort
import com.tenmm.tilserver.question.application.inbound.GetQuestionUseCase
import com.tenmm.tilserver.question.adapter.inbound.model.GetQuestionResponse
import com.tenmm.tilserver.question.adapter.inbound.model.Question

import org.springframework.stereotype.Service

@Service
class GetQuestionService(
    private val getQuestionPort: GetQuestionPort,
) : GetQuestionUseCase {
    override fun getQuestionByType(questionType: String): GetQuestionResponse {
        val question = getQuestionPort.findByQuestionType(questionType)
        val questions = question.map {
            Question(
                questionName = it.questionName
            )
        }
        return GetQuestionResponse(
            questionType = questionType,
            question = questions
        )
    }
}
