package com.tenmm.tilserver.user.application.service

import com.tenmm.tilserver.account.application.inbound.ModifyAccountUseCase
import com.tenmm.tilserver.blog.application.inbound.ModifyBlogUseCase
import com.tenmm.tilserver.blog.application.inbound.model.ModifyBlogCommand
import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.common.domain.ModifyUserFailException
import com.tenmm.tilserver.common.domain.ModifyUserFailType
import com.tenmm.tilserver.common.domain.OperationResult
import com.tenmm.tilserver.common.domain.Url
import com.tenmm.tilserver.common.domain.UserNotFoundException
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
        getUserPort.getByUserIdentifier(command.userIdentifier) ?: throw UserNotFoundException()
        getUserPort.getByPath(command.path)?.let {
            if (it.identifier != command.userIdentifier)
                throw ModifyUserFailException(ModifyUserFailType.USER_PATH)
        }
        modifyUserPort.updateUserName(command.userIdentifier, command.name).check(UpdateUserType.NAME)
        modifyUserPort.updateUserCategory(command.userIdentifier, command.categoryIdentifier)
            .check(UpdateUserType.CATEGORY)
        modifyUserPort.updateUserIntroduction(command.userIdentifier, command.introduction)
            .check(UpdateUserType.INTRODUCTION)
        modifyUserPort.updateUserProfileImgSrc(command.userIdentifier, command.profileImgSrc)
            .check(UpdateUserType.PROFILE_IMG_SRC)
        modifyUserPort.updateUserPath(command.userIdentifier, command.path).check(UpdateUserType.PATH)
        updateMailAgreement(command.userIdentifier, command.mailAgreement)
        updateBlogs(command.userIdentifier, command.blogs)
        return OperationResult.success()
    }

    @Transactional
    override fun onBoardingUserInfo(command: OnBoardingUserCommand): OperationResult {
        getUserPort.getByUserIdentifier(command.userIdentifier) ?: throw UserNotFoundException()
        modifyUserPort.updateUserCategory(command.userIdentifier, command.categoryIdentifier)
            .check(UpdateUserType.CATEGORY)
        modifyUserPort.updateUserStatus(command.userIdentifier).check(UpdateUserType.STATUS)
        updateMailAgreement(command.userIdentifier, command.mailAgreement)
        return OperationResult.success()
    }

    private fun updateMailAgreement(userIdentifier: Identifier, mailAgreement: Boolean) {
        val result = modifyAccountUseCase.modifyMailAgreement(
            userIdentifier = userIdentifier,
            isOn = mailAgreement
        )
        if (!result.isSuccess) {
            throw ModifyUserFailException(ModifyUserFailType.MAIL_AGREEMENT)
        }
    }

    private fun updateBlogs(userIdentifier: Identifier, blogs: List<Url>) {
        val result = modifyBlogUseCase.modify(
            ModifyBlogCommand(
                urls = blogs,
                userIdentifier = userIdentifier
            )
        )
        if (!result.isSuccess) {
            throw ModifyUserFailException(ModifyUserFailType.BLOG)
        }
    }

    enum class UpdateUserType {
        NAME, CATEGORY, INTRODUCTION, PROFILE_IMG_SRC, PATH, STATUS;

        fun toErrorCode(): ModifyUserFailType {
            return when (this) {
                NAME -> ModifyUserFailType.USER_NAME
                CATEGORY -> ModifyUserFailType.USER_CATEGORY
                INTRODUCTION -> ModifyUserFailType.USER_INTRODUCTION
                PROFILE_IMG_SRC -> ModifyUserFailType.USER_PROFILE_IMG_SRC
                PATH -> ModifyUserFailType.USER_PATH
                STATUS -> ModifyUserFailType.USER_STATUS
            }
        }
    }

    private fun OperationResult.check(type: UpdateUserType) {
        if (!this.isSuccess) {
            throw ModifyUserFailException(type.toErrorCode())
        }
    }
}
