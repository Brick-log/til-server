package com.tenmm.tilserver.retrospect.application.inbound

import com.tenmm.tilserver.common.domain.Identifier
interface DeleteRetrospectUseCase {
    fun deleteRetrospect(userIdentifier: Identifier, retrospectIdentifier: Identifier)
}
