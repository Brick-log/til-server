package com.tenmm.tilserver.category.adapter.inbound

import com.tenmm.tilserver.category.adapter.inbound.model.Category
import com.tenmm.tilserver.category.adapter.inbound.model.GetCategoriesResponse
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
                    id = RandomStringUtils.random(100),
                    name = RandomStringUtils.random(100),
                    description = RandomStringUtils.random(100)
                )
            )
        )
    }
}
