package com.tenmm.tilserver.user.application.inbound

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.common.domain.OperationResult

interface ModifyUserUseCase {
    fun modifyPath(userIdentifier: Identifier, path: String): OperationResult
    fun modifyName(userIdentifier: Identifier, name: String): OperationResult
    fun modifyIntroduction(userIdentifier: Identifier, introduction: String): OperationResult
    fun modifyCategory(userIdentifier: Identifier, categoryIdentifier: Identifier): OperationResult
}
