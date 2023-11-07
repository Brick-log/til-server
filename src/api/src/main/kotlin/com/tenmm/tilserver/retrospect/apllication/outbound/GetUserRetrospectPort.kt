package com.tenmm.tilserver.retrospect.application.outbound

import java.sql.Timestamp
import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.outbound.persistence.entity.RetrospectEntity
import com.tenmm.tilserver.outbound.persistence.entity.RetrospectQnaEntity

interface GetUserRetrospectPort {
    fun getRetrospectListByCreatedAt(userIdentifier: Identifier, to: Timestamp, from: Timestamp): List<RetrospectEntity>
    fun getRetrospectListByRetrospectIdentifier(retrospectIdentifier: Identifier): List<RetrospectQnaEntity>
    fun totalRetrospectCountByUser(userIdentifier: Identifier): Int
}
