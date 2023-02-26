package com.tenmm.tilserver.post.adapter.outbound.persistence.model

import java.time.LocalDateTime
import nonapi.io.github.classgraph.json.Id

class PostEntity(
    @field:Id
    // @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    // @field:Column
    val categoryIdentifier: String,

    // @field:Column
    val userIdentifier: String,

    // @field:Column
    val url: String,

    // @field:Column
    val title: String,

    // @field:Column
    val description: String,

    // @field:Column
    val hitCount: Int,

    // @field:Column
    val createdAt: LocalDateTime
)
