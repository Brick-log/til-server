package com.tenmm.tilserver.question.adapter.inbound.model

data class GetQuestionTypeResponse(
    val types: List<QuestionType>
)

data class QuestionType(
    val questionType: String,
    val questionTypeName: String,
)
