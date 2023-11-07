package com.tenmm.tilserver.retrospect.application.outbound

import com.tenmm.tilserver.outbound.persistence.entity.RetrospectEntity
import com.tenmm.tilserver.outbound.persistence.entity.RetrospectQnaEntity
import com.tenmm.tilserver.common.domain.Identifier

interface GetRetrospectPort {
    fun findOneRetrospectByUserIdentifierToday(userIdentifier: Identifier): RetrospectEntity?
    fun findRetrospectQnaListByRetrospectIdentifier(retrospectIdentifier: Identifier): List<RetrospectQnaEntity>
}
