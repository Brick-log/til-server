package com.tenmm.tilserver.question.application.inbound

import com.tenmm.tilserver.question.adapter.inbound.model.GetQuestionResponse

interface GetQuestionUseCase {
    fun getQuestionByType(type: String): GetQuestionResponse
    fun getPartOfQuestionList(): List<GetQuestionResponse>
}
