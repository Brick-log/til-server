package com.tenmm.tilserver.user.adapter.inbound.model

import com.tenmm.tilserver.common.domain.Identifier

data class ModifyUserCategoryRequest(
    val categoryIdentifier: Identifier,
)
