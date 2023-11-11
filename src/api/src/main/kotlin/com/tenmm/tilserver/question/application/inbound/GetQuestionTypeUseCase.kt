package com.tenmm.tilserver.question.application.inbound

import com.tenmm.tilserver.question.adapter.inbound.model.GetQuestionTypeResponse

interface GetQuestionTypeUseCase {
    fun getQuestionTypeList(): GetQuestionTypeResponse
}
