package com.tenmm.tilserver.question.adapter.inbound.model

data class GetQuestionResponse(
    val type: String,
    val question: List<Question>,
)

data class Question(
    val name: String,
)
