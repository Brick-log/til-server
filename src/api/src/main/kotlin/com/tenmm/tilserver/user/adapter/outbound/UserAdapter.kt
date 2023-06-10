package com.tenmm.tilserver.user.adapter.outbound

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.common.domain.OperationResult
import com.tenmm.tilserver.common.domain.Url
import com.tenmm.tilserver.outbound.persistence.entity.UserEntity
import com.tenmm.tilserver.outbound.persistence.entity.UserStatus
import com.tenmm.tilserver.outbound.persistence.repository.UserRepository
import com.tenmm.tilserver.user.application.outbound.GetUserPort
import com.tenmm.tilserver.user.application.outbound.ModifyUserPort
import com.tenmm.tilserver.user.application.outbound.SaveUserPort
import com.tenmm.tilserver.user.application.outbound.model.CreateUserRequest
import com.tenmm.tilserver.user.domain.User
import mu.KotlinLogging
import org.springframework.stereotype.Component

private val logger = KotlinLogging.logger {}

@Component
class UserAdapter(
    private val userRepository: UserRepository,
) : GetUserPort, SaveUserPort, ModifyUserPort {
    override fun getByUserIdentifier(identifier: Identifier): User? {
        return userRepository.findByUserIdentifier(identifier.value)?.toModel()
    }

    override fun getByName(name: String): User? {
        return userRepository.findByName(name)?.toModel()
    }

    override fun getByPath(path: String): User? {
        return userRepository.findByPath(path)?.toModel()
    }

    override fun getByUserIdentifierBulk(identifiers: List<Identifier>): List<User> {
        return userRepository.findByUserIdentifierIn(identifiers.map { it.value }).map { it.toModel() }
    }

    override fun save(command: CreateUserRequest): User? {
        return try {
            val user = userRepository.save(
                UserEntity(
                    name = command.name,
                    userIdentifier = command.userIdentifier.value,
                    path = command.path,
                    thumbnailUrl = command.profileImgSrc.value,
                    introduction = command.description
                )
            )
            user.toModel()
        } catch (e: Exception) {
            logger.error(e) { "User save Fail - $command" }
            null
        }
    }

    override fun updateUserName(userIdentifier: Identifier, name: String): OperationResult {
        try {
            val userEntity = userRepository.findByUserIdentifier(userIdentifier.value)
                ?: return OperationResult.fail()

            val modifiedUserEntity = userEntity.copy(
                name = name,
            )

            userRepository.save(modifiedUserEntity)
            return OperationResult.success()
        } catch (e: Exception) {
            logger.error(e) { "Modify user fail" }
            return OperationResult.fail()
        }
    }

    override fun updateUserPath(userIdentifier: Identifier, path: String): OperationResult {
        try {
            val userEntity = userRepository.findByUserIdentifier(userIdentifier.value)
                ?: return OperationResult.fail()

            val modifiedUserEntity = userEntity.copy(
                path = path,
            )

            userRepository.save(modifiedUserEntity)
            return OperationResult.success()
        } catch (e: Exception) {
            logger.error(e) { "Modify user fail" }
            return OperationResult.fail()
        }
    }

    override fun updateUserCategory(userIdentifier: Identifier, categoryIdentifier: Identifier): OperationResult {
        try {
            val userEntity = userRepository.findByUserIdentifier(userIdentifier.value)
                ?: return OperationResult.fail()

            val modifiedUserEntity = userEntity.copy(
                categoryIdentifier = categoryIdentifier.value,
            )

            userRepository.save(modifiedUserEntity)
            return OperationResult.success()
        } catch (e: Exception) {
            logger.error(e) { "Modify user fail" }
            return OperationResult.fail()
        }
    }

    override fun updateUserIntroduction(userIdentifier: Identifier, introduction: String): OperationResult {
        try {
            val userEntity = userRepository.findByUserIdentifier(userIdentifier.value)
                ?: return OperationResult.fail()

            val modifiedUserEntity = userEntity.copy(
                introduction = introduction,
            )

            userRepository.save(modifiedUserEntity)
            return OperationResult.success()
        } catch (e: Exception) {
            logger.error(e) { "Modify user fail" }
            return OperationResult.fail()
        }
    }

    override fun updateUserProfileImgSrc(userIdentifier: Identifier, profileImgSrc: Url): OperationResult {
        try {
            val userEntity = userRepository.findByUserIdentifier(userIdentifier.value)
                ?: return OperationResult.fail()

            val modifiedUserEntity = userEntity.copy(
                thumbnailUrl = profileImgSrc.value,
            )

            userRepository.save(modifiedUserEntity)
            return OperationResult.success()
        } catch (e: Exception) {
            logger.error(e) { "Modify user fail" }
            return OperationResult.fail()
        }
    }

    override fun updateUserStatus(userIdentifier: Identifier): OperationResult {
        try {
            val userEntity = userRepository.findByUserIdentifier(userIdentifier.value)
                ?: return OperationResult.fail()

            val modifiedUserEntity = userEntity.copy(
                status = UserStatus.COMPLETED,
            )

            userRepository.save(modifiedUserEntity)
            return OperationResult.success()
        } catch (e: Exception) {
            logger.error(e) { "Modify user fail" }
            return OperationResult.fail()
        }
    }
}
