package com.tenmm.tilserver.retrospect.adapter.inbound.Model

import java.sql.Timestamp

data class DetailRetrospect(
    val isSecret: Boolean,
    val id: String,
    val createdAt: Timestamp,
    val qna: List<RetrospectQna>
)
