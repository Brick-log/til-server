package com.tenmm.tilserver.user.adapter.inbound.model

data class ModifyUserRequest(
    val categoryIdentifier: String,
    val introduction: String,
    val name: String,
    val path: String,
    val profileImgSrc: String,
    val isMailAgreement: Boolean,
)
