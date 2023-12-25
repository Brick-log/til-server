package com.tenmm.tilserver.question.adapter.inbound

import com.tenmm.tilserver.common.exception.ErrorResponse
import com.tenmm.tilserver.question.application.inbound.GetQuestionTypeUseCase
import com.tenmm.tilserver.question.adapter.inbound.model.GetQuestionTypeResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/question")
@Tag(name = "Question")
class GetQuestionTypeController(
    private val getQuestionTypeUseCase: GetQuestionTypeUseCase,
) {

    @GetMapping("")
    @Operation(
        summary = "전체 질문 타입 요청",
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "질문 타입 요청 성공"
            ),
            ApiResponse(
                responseCode = "400",
                description = "잘못된 질문 타입 요청 (ex.잘못된 id)",
                content = [Content(schema = Schema(implementation = ErrorResponse::class))]
            ),
            ApiResponse(
                responseCode = "404",
                description = "질문 타입 조회 실패",
                content = [Content(schema = Schema(implementation = ErrorResponse::class))]
            ),
            ApiResponse(
                responseCode = "500",
                description = "서버에러",
                content = [Content(schema = Schema(implementation = ErrorResponse::class))]
            )
        ]
    )
    fun getAllQuestionTpyeList(): GetQuestionTypeResponse {
        val questionType = getQuestionTypeUseCase.getQuestionTypeList()
        return questionType
    }

    @GetMapping("/randomQuestionType")
    @Operation(
        summary = "고정 질문 + 랜덤 질문 타입 요청 (질문 타입만)",
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
    fun getPartOfQuestionTypeList(): GetQuestionTypeResponse {
        val question = getQuestionTypeUseCase.getPartOfQuestionTypeList()
        return question
    }
}
