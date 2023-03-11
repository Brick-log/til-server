package com.tenmm.tilserver.outbound.persistence.repository

import com.tenmm.tilserver.outbound.persistence.entity.AlarmEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AlarmRepository : JpaRepository<AlarmEntity, Long> {
    fun findByUserIdentifier(userIdentifier: String): AlarmEntity?
}
