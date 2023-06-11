package com.tenmm.tilserver.user.application.outbound

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.common.domain.OperationResult
import com.tenmm.tilserver.common.domain.Url

interface ModifyUserPort {
    fun updateUserName(userIdentifier: Identifier, name: String): OperationResult
    fun updateUserPath(userIdentifier: Identifier, path: String): OperationResult
    fun updateUserCategory(userIdentifier: Identifier, categoryIdentifier: Identifier): OperationResult
    fun updateUserIntroduction(userIdentifier: Identifier, introduction: String): OperationResult
    fun updateUserProfileImgSrc(userIdentifier: Identifier, profileImgSrc: Url): OperationResult
    fun updateUserStatus(userIdentifier: Identifier): OperationResult
}
