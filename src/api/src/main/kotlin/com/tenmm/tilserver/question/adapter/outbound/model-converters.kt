package com.tenmm.tilserver.question.adapter.outbound

import com.tenmm.tilserver.outbound.persistence.entity.QuestionEntity
import com.tenmm.tilserver.outbound.persistence.entity.QuestionTypeEntity
import com.tenmm.tilserver.question.domain.Question
import com.tenmm.tilserver.question.domain.QuestionType

fun Question.toEntity(): QuestionEntity {
    return QuestionEntity(
        type = this.type,
        name = this.name,
    )
}

fun QuestionEntity.toModel(): Question {
    return Question(
        type = this.type,
        name = this.name,
    )
}

fun QuestionType.toEntity(): QuestionTypeEntity {
    return QuestionTypeEntity(
        type = this.type,
        name = this.name,
    )
}

fun QuestionTypeEntity.toModel(): QuestionType {
    return QuestionType(
        type = this.type,
        name = this.name,
    )
}
