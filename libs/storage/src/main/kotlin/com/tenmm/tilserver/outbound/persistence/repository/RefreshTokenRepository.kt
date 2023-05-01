package com.tenmm.tilserver.outbound.persistence.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import com.tenmm.tilserver.outbound.persistence.entity.RefreshTokenEntity

@Repository
interface RefreshTokenRepository : CrudRepository<RefreshTokenEntity, String> {
    fun findByUserIdentifier(userIdentifier: String): RefreshTokenEntity?
}
