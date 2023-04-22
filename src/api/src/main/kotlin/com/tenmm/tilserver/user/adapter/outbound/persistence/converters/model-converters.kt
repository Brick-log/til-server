package com.tenmm.tilserver.user.adapter.outbound.persistence.converters

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.common.domain.Url
import com.tenmm.tilserver.outbound.persistence.entity.UserEntity
import com.tenmm.tilserver.user.domain.User
import com.tenmm.tilserver.user.domain.UserStatus

fun User.toEntity(): UserEntity {
    return UserEntity(
        name = this.name,
        userIdentifier = this.identifier.value,
        categoryIdentifier = this.categoryIdentifier?.value,
        introduction = this.introduction,
        thumbnailUrl = this.thumbnailUrl.value,
        path = this.path
    )
}
fun UserEntity.toModel(): User {
    return User(
        name = this.name,
        path = this.path,
        introduction = this.introduction,
        thumbnailUrl = Url(this.thumbnailUrl),
        identifier = Identifier(this.userIdentifier),
        status = UserStatus.valueOf(this.status.name),
        categoryIdentifier = this.categoryIdentifier?.let { Identifier(it) }
    )
}
