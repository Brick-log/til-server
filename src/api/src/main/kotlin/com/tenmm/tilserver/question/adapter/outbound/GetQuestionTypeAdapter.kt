package com.tenmm.tilserver.QuestionType.adapter.outbound

import com.tenmm.tilserver.outbound.persistence.repository.QuestionTypeRepository
import com.tenmm.tilserver.outbound.persistence.entity.QuestionTypeEntity
import com.tenmm.tilserver.question.application.outbound.GetQuestionTypePort
import org.springframework.stereotype.Component

@Component
class GetQuestionTypeAdapter(
    private val questionTypeRepository: QuestionTypeRepository,
) : GetQuestionTypePort {
    override fun findAll(): List<QuestionTypeEntity> {
        return questionTypeRepository.findAll()
    }
}
