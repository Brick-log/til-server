package com.tenmm.tilserver.user.application.outbound

import com.tenmm.tilserver.user.application.inbound.model.ModifyUserCommand
import com.tenmm.tilserver.user.application.inbound.model.OnBoardingUserCommand

interface ModifyUserPort {
    fun modifyUserInfo(command: ModifyUserCommand): Boolean
    fun modifyUserInfo(command: OnBoardingUserCommand): Boolean
}
