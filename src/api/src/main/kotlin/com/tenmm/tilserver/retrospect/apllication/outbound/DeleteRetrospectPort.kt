package com.tenmm.tilserver.retrospect.application.outbound

import com.tenmm.tilserver.common.domain.Identifier

interface DeleteRetrospectPort {
    fun delete(userIdentifier: Identifier, retrospectIdentifier: Identifier)
}
