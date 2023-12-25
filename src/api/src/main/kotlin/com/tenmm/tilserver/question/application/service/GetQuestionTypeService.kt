package com.tenmm.tilserver.post.application.service

import com.tenmm.tilserver.question.application.outbound.GetQuestionTypePort
import com.tenmm.tilserver.question.application.inbound.GetQuestionTypeUseCase
import com.tenmm.tilserver.question.adapter.inbound.model.GetQuestionTypeResponse
import com.tenmm.tilserver.question.adapter.inbound.model.QuestionType
import org.springframework.stereotype.Service

@Service
class GetQuestionTypeService(
    private val getQuestionTypePort: GetQuestionTypePort,
) : GetQuestionTypeUseCase {
    override fun getQuestionTypeList(): GetQuestionTypeResponse {
        val questionType = getQuestionTypePort.findAll()
        val questionTypes = questionType.map {
            QuestionType(
                questionType = it.questionType,
                questionTypeName = it.questionTypeName,
                isRandom = it.isRandom
            )
        }
        return GetQuestionTypeResponse(
            types = questionTypes
        )
    }

    override fun getPartOfQuestionTypeList(): GetQuestionTypeResponse {
        val randomQuestionType = getQuestionTypePort.findRandomQuestion()
        val randomQuestionTypes = randomQuestionType.map {
            QuestionType(
                questionType = it.questionType,
                questionTypeName = it.questionTypeName,
                isRandom = it.isRandom
            )
        }
        val staticuestionType = getQuestionTypePort.findStaticQuestion()
        val staticQuestionTypes = staticuestionType.map {
            QuestionType(
                questionType = it.questionType,
                questionTypeName = it.questionTypeName,
                isRandom = it.isRandom
            )
        }
        return GetQuestionTypeResponse(
            types = staticQuestionTypes + randomQuestionTypes.shuffled().take(1)
        )
    }

    override fun getQuestionType(questionType: String): QuestionType {
        val questionTypeModel = getQuestionTypePort.findByType(questionType)
        return QuestionType(
            questionType = questionType,
            questionTypeName = questionTypeModel.questionTypeName,
            isRandom = questionTypeModel.isRandom
        )
    }
}
