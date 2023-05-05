package com.tenmm.tilserver.user.adapter.outbound

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.outbound.persistence.entity.UserEntity
import com.tenmm.tilserver.outbound.persistence.repository.UserRepository
import com.tenmm.tilserver.user.application.inbound.model.CreateUserCommand
import com.tenmm.tilserver.user.application.inbound.model.ModifyUserCommand
import com.tenmm.tilserver.user.application.inbound.model.OnBoardingUserCommand
import com.tenmm.tilserver.user.application.outbound.GetUserPort
import com.tenmm.tilserver.user.application.outbound.ModifyUserPort
import com.tenmm.tilserver.user.application.outbound.SaveUserPort
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

    override fun modifyUserInfo(command: ModifyUserCommand): Boolean {
        val userEntity = userRepository.findByUserIdentifier(command.userIdentifier.value)
            ?: return false

        val modifiedUserEntity = userEntity.copy(
            path = command.path,
            name = command.name,
            introduction = command.introduction,
            categoryIdentifier = command.categoryIdentifier.value
        )
        userRepository.save(modifiedUserEntity)
        return true
    }

    override fun modifyUserInfo(command: OnBoardingUserCommand): Boolean {
        val userEntity = userRepository.findByUserIdentifier(command.userIdentifier.value)
            ?: return false

        val modifiedUserEntity = userEntity.copy(
            categoryIdentifier = command.categoryIdentifier.value
        )

        userRepository.save(modifiedUserEntity)
        return true
    }

    override fun save(command: CreateUserCommand): User? {
        return try {
            val user = userRepository.save(
                UserEntity(
                    name = command.name,
                    userIdentifier = command.userIdentifier.value,
                    introduction = command.introduction,
                    thumbnailUrl = command.thumbnailUrl.value,
                    path = command.name
                )
            )
            user.toModel()
        } catch (e: Exception) {
            logger.error(e) { "User save Fail - $command" }
            null
        }
    }
}
