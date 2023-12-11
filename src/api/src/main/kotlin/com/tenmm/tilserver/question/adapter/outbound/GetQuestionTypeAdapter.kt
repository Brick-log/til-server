package com.tenmm.tilserver.QuestionType.adapter.outbound

import com.tenmm.tilserver.outbound.persistence.repository.QuestionTypeRepository
import com.tenmm.tilserver.outbound.persistence.entity.QuestionTypeEntity
import com.tenmm.tilserver.question.application.outbound.GetQuestionTypePort
import com.tenmm.tilserver.common.domain.QuestionTypeNotFoundException
import org.springframework.stereotype.Component

@Component
class GetQuestionTypeAdapter(
    private val questionTypeRepository: QuestionTypeRepository,
) : GetQuestionTypePort {
    override fun findAll(): List<QuestionTypeEntity> {
        return questionTypeRepository.findAll()
    }
    override fun findByType(questionType: String): QuestionTypeEntity {
        return questionTypeRepository.findByType(questionType) ?: throw QuestionTypeNotFoundException()
    }
}
