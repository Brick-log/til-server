package com.tenmm.tilserver.user.adapter.outbound.persistence

import com.tenmm.tilserver.outbound.persistence.entity.UserEntity
import com.tenmm.tilserver.outbound.persistence.repository.UserRepository
import com.tenmm.tilserver.user.adapter.outbound.persistence.converters.toModel
import com.tenmm.tilserver.user.application.inbound.model.CreateUserCommand
import com.tenmm.tilserver.user.application.outbound.SaveUserPort
import com.tenmm.tilserver.user.domain.User
import mu.KotlinLogging
import org.springframework.stereotype.Component

private val logger = KotlinLogging.logger {}

@Component
class SaveUserAdapter(
    private val userRepository: UserRepository
) : SaveUserPort {
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
