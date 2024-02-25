package com.tenmm.tilserver.retrospect.application.inbound.Model

import java.sql.Timestamp

data class RetrospectDto(
    val retrospectIdentifier: String?,
    val categoryIdentifier: String?,
    val userIdentifier: String?,
    val questionType: String?,
    val isSecret: Boolean?,
    val createdAt: Timestamp?
)
