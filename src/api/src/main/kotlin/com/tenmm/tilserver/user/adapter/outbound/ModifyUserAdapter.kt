package com.tenmm.tilserver.user.adapter.outbound

import com.tenmm.tilserver.outbound.persistence.repository.UserRepository
import com.tenmm.tilserver.user.application.inbound.model.ModifyUserCommand
import com.tenmm.tilserver.user.application.outbound.ModifyUserPort
import org.springframework.stereotype.Component

@Component
class ModifyUserAdapter(
    private val userRepository: UserRepository,
) : ModifyUserPort {
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
}
