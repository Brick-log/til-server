package com.tenmm.tilserver.question.adapter.inbound

import com.tenmm.tilserver.common.exception.ErrorResponse
import com.tenmm.tilserver.question.application.inbound.GetQuestionUseCase
import com.tenmm.tilserver.question.adapter.inbound.model.GetQuestionResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/question")
@Tag(name = "Question")
class GetQuestionController(
    private val getQuestionUseCase: GetQuestionUseCase,
) {

    @GetMapping("/{questionType}")
    @Operation(
        summary = "질문 요청",
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "질문 요청 성공"
            ),
            ApiResponse(
                responseCode = "400",
                description = "잘못된 질문 요청 (ex.잘못된 id)",
                content = [Content(schema = Schema(implementation = ErrorResponse::class))]
            ),
            ApiResponse(
                responseCode = "404",
                description = "질문 조회 실패",
                content = [Content(schema = Schema(implementation = ErrorResponse::class))]
            ),
            ApiResponse(
                responseCode = "500",
                description = "서버에러",
                content = [Content(schema = Schema(implementation = ErrorResponse::class))]
            )
        ]
    )
    fun getQuestionByType(
        @PathVariable questionType: String
    ): GetQuestionResponse {
        val question = getQuestionUseCase.getQuestionByType(questionType)
        return question
    }
}
