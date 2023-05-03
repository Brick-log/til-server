package com.tenmm.tilserver.user.application.inbound.model

import com.tenmm.tilserver.common.domain.Identifier

data class OnBoardingUserCommand(
    val userIdentifier: Identifier,
    val categoryIdentifier: Identifier,
    val mailAgreement: Boolean,
)
