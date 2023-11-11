package com.tenmm.tilserver.question.application.outbound

import com.tenmm.tilserver.outbound.persistence.entity.QuestionEntity

interface GetQuestionPort {
    fun findByType(type: String): List<QuestionEntity>
}
