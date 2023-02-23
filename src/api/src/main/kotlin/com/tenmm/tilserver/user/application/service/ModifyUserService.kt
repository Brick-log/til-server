package com.tenmm.tilserver.user.application.service

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.common.domain.OperationResult
import com.tenmm.tilserver.user.application.inbound.ModifyUserUseCase
import org.springframework.stereotype.Service

@Service
class ModifyUserService : ModifyUserUseCase {
    override fun modifyPath(userIdentifier: Identifier, path: String): OperationResult {
        TODO("Not yet implemented")
    }

    override fun modifyName(userIdentifier: Identifier, name: String): OperationResult {
        TODO("Not yet implemented")
    }

    override fun modifyIntroduction(userIdentifier: Identifier, introduction: String): OperationResult {
        TODO("Not yet implemented")
    }

    override fun modifyCategory(userIdentifier: Identifier, categoryIdentifier: Identifier): OperationResult {
        TODO("Not yet implemented")
    }
}
