package com.tenmm.tilserver.question.adapter.inbound.model

data class GetQuestionTypeResponse(
    val types: List<QuestionType>
)

data class QuestionType(
    val type: String,
    val name: String,
)
