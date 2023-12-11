package com.tenmm.tilserver.question.application.inbound

import com.tenmm.tilserver.question.adapter.inbound.model.GetQuestionTypeResponse
import com.tenmm.tilserver.question.adapter.inbound.model.QuestionType

interface GetQuestionTypeUseCase {
    fun getQuestionTypeList(): GetQuestionTypeResponse
    fun getQuestionType(questionType: String): QuestionType
}
