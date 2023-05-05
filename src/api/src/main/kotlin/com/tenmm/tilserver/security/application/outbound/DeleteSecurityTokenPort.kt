package com.tenmm.tilserver.security.application.outbound

import com.tenmm.tilserver.common.domain.Identifier

interface DeleteSecurityTokenPort {
    suspend fun delete(userIdentifier: Identifier, accessToken: String)
}
