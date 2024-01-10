package com.tenmm.tilserver.retrospect.adapter.inbound.Model

import java.sql.Timestamp

data class DetailRetrospect(
    val isSecret: Boolean,
    val retrospectIdentifier: String,
    val userName: String,
    val userPath: String,
    val profileImgSrc: String,
    val categoryIdentifier: String,
    val categoryName: String,
    val createdAt: Timestamp,
    val questionType: String,
    val questionTypeName: String,
    val qna: List<RetrospectQna>
)
