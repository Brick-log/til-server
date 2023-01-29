package com.tenmm.tilserver.category.adapter.inbound.rest

import com.tenmm.tilserver.category.adapter.inbound.rest.model.GetCategoriesResponse
import com.tenmm.tilserver.category.domain.Category
import org.apache.commons.lang3.RandomStringUtils
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/categories")
class GetCategoryController {
    @GetMapping
    fun getCategories(): GetCategoriesResponse {
        return GetCategoriesResponse(
            categories = listOf(
                Category(
                    identifier = RandomStringUtils.random(100),
                    name = RandomStringUtils.random(100),
                )
            )
        )
    }
}
