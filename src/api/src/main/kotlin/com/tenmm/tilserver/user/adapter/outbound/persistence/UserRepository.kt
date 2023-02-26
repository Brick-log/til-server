package com.tenmm.tilserver.user.adapter.outbound.persistence

import com.tenmm.tilserver.user.adapter.outbound.persistence.model.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<UserEntity, Long>
