package com.tenmm.tilserver.retrospect.adapter.inbound

import com.tenmm.tilserver.retrospect.application.inbound.GetCategoryRetrospectUseCase

import io.swagger.v3.oas.annotations.tags.Tag

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping

@RestController
@RequestMapping("/v1/retrospect/category")
@Tag(name = "Retrospect")
class GetCategoryRetrospectController(
    private val getCategoryRetrospectUseCase: GetCategoryRetrospectUseCase,
)
