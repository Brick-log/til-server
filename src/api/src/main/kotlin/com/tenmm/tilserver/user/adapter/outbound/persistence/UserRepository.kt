package com.tenmm.tilserver.user.adapter.outbound.persistence

import com.tenmm.tilserver.user.adapter.outbound.persistence.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long>
