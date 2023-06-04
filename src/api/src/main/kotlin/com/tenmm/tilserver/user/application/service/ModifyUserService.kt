package com.tenmm.tilserver.user.application.service

import com.tenmm.tilserver.account.application.inbound.ModifyAccountUseCase
import com.tenmm.tilserver.blog.application.inbound.ModifyBlogUseCase
import com.tenmm.tilserver.blog.application.inbound.model.ModifyBlogCommand
import com.tenmm.tilserver.common.domain.ModifyFailException
import com.tenmm.tilserver.common.domain.ModifyFailType
import com.tenmm.tilserver.common.domain.NotFoundException
import com.tenmm.tilserver.common.domain.OperationResult
import com.tenmm.tilserver.user.application.inbound.ModifyUserUseCase
import com.tenmm.tilserver.user.application.inbound.model.ModifyUserCommand
import com.tenmm.tilserver.user.application.inbound.model.OnBoardingUserCommand
import com.tenmm.tilserver.user.application.outbound.GetUserPort
import com.tenmm.tilserver.user.application.outbound.ModifyUserPort
import mu.KotlinLogging
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

private val logger = KotlinLogging.logger {}

@Service
class ModifyUserService(
    private val getUserPort: GetUserPort,
    private val modifyUserPort: ModifyUserPort,
    private val modifyAccountUseCase: ModifyAccountUseCase,
    private val modifyBlogUseCase: ModifyBlogUseCase,
) : ModifyUserUseCase {

    @Transactional
    override fun modifyUserInfo(command: ModifyUserCommand): OperationResult {
        getUserPort.getByUserIdentifier(command.userIdentifier)
            ?: throw NotFoundException("User not found")

        try {
            modifyUserPort.modifyUserInfo(command)
        } catch (e: Exception) {
            logger.error(e) { "Modify USER Fail : $command" }
            throw ModifyFailException(ModifyFailType.USER)
        }

        modifyAccountUseCase.modifyMailAgreement(
            userIdentifier = command.userIdentifier,
            isOn = command.mailAgreement
        )
        modifyBlogUseCase.modify(
            ModifyBlogCommand(
                urls = command.blogs,
                userIdentifier = command.userIdentifier
            )
        )
        return OperationResult.success()
    }

    override fun onBoardingUserInfo(command: OnBoardingUserCommand): OperationResult {
        modifyUserPort.modifyUserInfo(command)
        modifyAccountUseCase.modifyMailAgreement(
            userIdentifier = command.userIdentifier,
            isOn = command.mailAgreement
        )
        return OperationResult.success()
    }
}
