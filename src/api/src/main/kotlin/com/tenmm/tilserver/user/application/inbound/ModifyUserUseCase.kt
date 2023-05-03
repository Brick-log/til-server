package com.tenmm.tilserver.user.application.inbound

import com.tenmm.tilserver.common.domain.OperationResult
import com.tenmm.tilserver.user.application.inbound.model.ModifyUserCommand
import com.tenmm.tilserver.user.application.inbound.model.OnBoardingUserCommand

interface ModifyUserUseCase {
    fun modifyUserInfo(command: ModifyUserCommand): OperationResult
    fun onBoardingUserInfo(command: OnBoardingUserCommand): OperationResult
}
