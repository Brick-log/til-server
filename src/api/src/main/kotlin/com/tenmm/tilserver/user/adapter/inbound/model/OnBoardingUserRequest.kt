package com.tenmm.tilserver.user.adapter.inbound.model

data class OnBoardingUserRequest(
    val categoryIdentifier: String,
    val mailAgreement: Boolean,
)
