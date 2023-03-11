package com.tenmm.tilserver.category.adapter.inbound.rest

import com.tenmm.tilserver.category.adapter.inbound.rest.model.CategoryResult
import com.tenmm.tilserver.category.adapter.inbound.rest.model.GetCategoriesResponse
import com.tenmm.tilserver.common.adapter.inbound.model.ErrorResponse
import com.tenmm.tilserver.common.domain.Identifier
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import java.util.UUID
import org.apache.commons.lang3.RandomStringUtils
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@Tag(name = "Category")
@RequestMapping("/v1/categories")
class GetCategoryController {
    @GetMapping
    @Operation(
        summary = "전체 카테고리 가져오기",
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "전체 category 조회성공"
            ),
            ApiResponse(
                responseCode = "500",
                description = "서버 에러",
                content = [Content(schema = Schema(implementation = ErrorResponse::class))]
            )
        ]
    )
    fun getCategories(): GetCategoriesResponse {
        return GetCategoriesResponse(
            categories = listOf(
                CategoryResult(
                    identifier = Identifier(UUID.randomUUID().toString()).toString(),
                    name = RandomStringUtils.randomAlphabetic(10),
                ),
                CategoryResult(
                    identifier = Identifier(UUID.randomUUID().toString()).toString(),
                    name = RandomStringUtils.randomAlphabetic(10),
                ),
                CategoryResult(
                    identifier = Identifier(UUID.randomUUID().toString()).toString(),
                    name = RandomStringUtils.randomAlphabetic(10),
                )
            )
        )
    }
}
