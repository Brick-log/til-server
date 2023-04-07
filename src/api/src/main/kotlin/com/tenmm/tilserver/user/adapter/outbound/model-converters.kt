package com.tenmm.tilserver.user.adapter.outbound

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.common.domain.Url
import com.tenmm.tilserver.outbound.persistence.entity.UserEntity
import com.tenmm.tilserver.outbound.persistence.entity.UserStatus
import com.tenmm.tilserver.user.domain.User
import com.tenmm.tilserver.user.domain.UserStatus as UserStatusModel

fun UserEntity.toModel(): User {
    return User(
        identifier = Identifier(this.userIdentifier),
        name = this.name,
        status = this.status.toModel(),
        path = this.path,
        introduction = this.introduction,
        thumbnailUrl = Url(this.profileImgSrc),
        categoryIdentifier = Identifier(this.categoryIdentifier),
    )
}

fun UserStatus.toModel(): UserStatusModel {
    return when (this) {
        UserStatus.ON_BOARDING -> UserStatusModel.ON_BOARDING
        UserStatus.COMPLETED -> UserStatusModel.COMPLETED
    }
}
