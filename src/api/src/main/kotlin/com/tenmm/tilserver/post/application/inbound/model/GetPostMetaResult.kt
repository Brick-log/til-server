package com.tenmm.tilserver.post.application.inbound.model

import java.sql.Timestamp

data class GetPostMetaResult(
    val dateList: List<Timestamp>,
)
