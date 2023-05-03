package com.tenmm.tilserver.user.application.service

import com.tenmm.tilserver.account.application.inbound.ModifyAccountUseCase
import com.tenmm.tilserver.common.domain.NotFoundException
import com.tenmm.tilserver.common.domain.OperationResult
import com.tenmm.tilserver.user.application.inbound.ModifyUserUseCase
import com.tenmm.tilserver.user.application.inbound.model.ModifyUserCommand
import com.tenmm.tilserver.user.application.inbound.model.OnBoardingUserCommand
import com.tenmm.tilserver.user.application.outbound.GetUserPort
import com.tenmm.tilserver.user.application.outbound.ModifyUserPort
import org.springframework.stereotype.Service

@Service
class ModifyUserService(
    private val getUserPort: GetUserPort,
    private val modifyUserPort: ModifyUserPort,
    private val modifyAccountUseCase: ModifyAccountUseCase,
) : ModifyUserUseCase {
    override fun modifyUserInfo(command: ModifyUserCommand): OperationResult {
        getUserPort.getByUserIdentifier(command.userIdentifier)
            ?: throw NotFoundException("ZZ")

        modifyUserPort.modifyUserInfo(command)
        modifyAccountUseCase.modifyMailAgreement(userIdentifier = command.userIdentifier, isOn = command.mailAgreement)
        return OperationResult.success()
    }

    override fun onBoardingUserInfo(command: OnBoardingUserCommand): OperationResult {
        modifyUserPort.modifyUserInfo(command)
        modifyAccountUseCase.modifyMailAgreement(userIdentifier = command.userIdentifier, isOn = command.mailAgreement)
        return OperationResult.success()
    }
}
