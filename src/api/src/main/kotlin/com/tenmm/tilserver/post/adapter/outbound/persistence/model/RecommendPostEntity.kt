package com.tenmm.tilserver.post.adapter.outbound.persistence.model

import java.time.LocalDateTime
import nonapi.io.github.classgraph.json.Id

class RecommendPostEntity(
    @field:Id
    // @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    // @field:Column
    val categoryIdentifier: String,

    // @field:Column
    val postIdentifier: String,

    // @field:Column
    val createdAt: LocalDateTime
)
