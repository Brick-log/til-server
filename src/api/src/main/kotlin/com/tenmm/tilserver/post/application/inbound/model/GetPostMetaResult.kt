package com.tenmm.tilserver.post.application.inbound.model

import java.time.LocalDate

data class GetPostMetaResult(
    val dateList: List<LocalDate>,
)
