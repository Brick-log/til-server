package com.tenmm.tilserver.outbound.persistence.repository

import com.tenmm.tilserver.outbound.persistence.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<UserEntity, Long> {
    fun findByUserIdentifier(userIdentifier: String): UserEntity?
    fun findByPath(path: String): UserEntity?
    fun findByName(name: String): UserEntity?
}
