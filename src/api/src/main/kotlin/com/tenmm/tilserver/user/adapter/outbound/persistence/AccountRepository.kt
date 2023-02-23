package com.tenmm.tilserver.user.adapter.outbound.persistence

import com.tenmm.tilserver.user.adapter.outbound.persistence.model.Account
import org.springframework.data.jpa.repository.JpaRepository

interface AccountRepository : JpaRepository<Account, Long>
