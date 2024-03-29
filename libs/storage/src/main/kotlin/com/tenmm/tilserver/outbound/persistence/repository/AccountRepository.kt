package com.tenmm.tilserver.outbound.persistence.repository

import com.tenmm.tilserver.outbound.persistence.entity.AccountEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AccountRepository : JpaRepository<AccountEntity, Long> {
    fun findByEmail(email: String): AccountEntity?
    fun findByUserIdentifier(userIdentifier: String): AccountEntity?
}
