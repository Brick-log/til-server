package com.tenmm.tilserver.question.adapter.inbound.model

data class GetQuestionResponse(
    val questionType: String,
    val questionTypeName: String,
    val isRandom: Boolean,
    val question: List<Question>,
)

data class Question(
    val questionName: String,
)
