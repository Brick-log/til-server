package com.tenmm.tilserver.user.application.inbound

import com.tenmm.tilserver.common.domain.OperationResult
import com.tenmm.tilserver.user.application.inbound.model.ModifyUserCommand

interface ModifyUserUseCase {
    fun modifyUserInfo(command: ModifyUserCommand): OperationResult
}
