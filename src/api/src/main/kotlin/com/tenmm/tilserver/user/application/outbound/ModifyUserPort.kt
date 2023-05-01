package com.tenmm.tilserver.user.application.outbound

import com.tenmm.tilserver.user.application.inbound.model.ModifyUserCommand

interface ModifyUserPort {
    fun modifyUserInfo(command: ModifyUserCommand): Boolean
}
