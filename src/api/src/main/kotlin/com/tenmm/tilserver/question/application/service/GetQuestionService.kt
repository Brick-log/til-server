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
    override fun getQuestionByType(type: String): GetQuestionResponse {
        val question = getQuestionPort.findByType(type)
        val questions = question.map {
            println(it.name)
            Question(
                name = it.name
            )
        }
        return GetQuestionResponse(
            type = type,
            question = questions
        )
    }
}
