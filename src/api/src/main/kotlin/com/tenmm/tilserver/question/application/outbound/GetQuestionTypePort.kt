package com.tenmm.tilserver.question.application.outbound

import com.tenmm.tilserver.outbound.persistence.entity.QuestionTypeEntity

interface GetQuestionTypePort {
    fun findAll(): List<QuestionTypeEntity>
}
