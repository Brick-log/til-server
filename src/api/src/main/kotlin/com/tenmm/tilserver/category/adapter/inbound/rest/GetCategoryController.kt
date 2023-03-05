package com.tenmm.tilserver.category.adapter.inbound.rest

import com.tenmm.tilserver.category.adapter.inbound.rest.model.GetCategoriesResponse
import com.tenmm.tilserver.category.domain.Category
import com.tenmm.tilserver.common.domain.Identifier
import io.swagger.v3.oas.annotations.tags.Tag
import java.util.UUID
import org.apache.commons.lang3.RandomStringUtils
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@Tag(name = "category")
@RequestMapping("/v1/categories")
class GetCategoryController {
    @GetMapping
    fun getCategories(): GetCategoriesResponse {
        return GetCategoriesResponse(
            categories = listOf(
                Category(
                    identifier = Identifier(UUID.randomUUID().toString()),
                    name = RandomStringUtils.randomAlphabetic(10),
                ),
                Category(
                    identifier = Identifier(UUID.randomUUID().toString()),
                    name = RandomStringUtils.randomAlphabetic(10),
                ),
                Category(
                    identifier = Identifier(UUID.randomUUID().toString()),
                    name = RandomStringUtils.randomAlphabetic(10),
                )
            )
        )
    }
}
