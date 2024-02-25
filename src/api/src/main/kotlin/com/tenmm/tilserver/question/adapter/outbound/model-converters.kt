package com.tenmm.tilserver.question.adapter.outbound

import com.tenmm.tilserver.outbound.persistence.entity.QuestionEntity
import com.tenmm.tilserver.outbound.persistence.entity.QuestionTypeEntity
import com.tenmm.tilserver.question.domain.Question
import com.tenmm.tilserver.question.domain.QuestionType

fun Question.toEntity(): QuestionEntity {
    return QuestionEntity(
        questionType = this.questionType,
        questionName = this.questionName,
    )
}

fun QuestionEntity.toModel(): Question {
    return Question(
        questionType = this.questionType,
        questionName = this.questionName,
    )
}

fun QuestionType.toEntity(): QuestionTypeEntity {
    return QuestionTypeEntity(
        questionType = this.questionType,
        questionTypeName = this.questionTypeName,
        isRandom = this.isRandom
    )
}

fun QuestionTypeEntity.toModel(): QuestionType {
    return QuestionType(
        questionType = this.questionType,
        questionTypeName = this.questionTypeName,
        isRandom = this.isRandom
    )
}
