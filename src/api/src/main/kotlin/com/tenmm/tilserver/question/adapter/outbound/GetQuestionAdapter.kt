package com.tenmm.tilserver.question.adapter.outbound

import com.tenmm.tilserver.outbound.persistence.repository.QuestionRepository
import com.tenmm.tilserver.question.application.outbound.GetQuestionPort
import com.tenmm.tilserver.outbound.persistence.entity.QuestionEntity
import org.springframework.stereotype.Component

@Component
class GetQuestionAdapter(
    private val questionRepository: QuestionRepository,
) : GetQuestionPort {
    override fun findByQuestionType(questionType: String): List<QuestionEntity> {
        return questionRepository.findByQuestionType(questionType)
    }
}
