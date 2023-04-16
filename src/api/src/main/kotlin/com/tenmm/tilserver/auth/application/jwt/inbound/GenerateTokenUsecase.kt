package com.tenmm.tilserver.auth.application.jwt.inbound

import com.tenmm.tilserver.auth.adapter.inboud.model.TokenResModel

interface GenerateTokenUsecase {
    fun generate(userIdentifier: String): TokenResModel
}
