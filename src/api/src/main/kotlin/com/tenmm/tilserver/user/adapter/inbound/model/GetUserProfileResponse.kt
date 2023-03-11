package com.tenmm.tilserver.user.adapter.inbound.model

import com.tenmm.tilserver.common.domain.Url
import com.tenmm.tilserver.user.domain.User

data class GetUserProfileResponse(
    val name: String,
    val profileImgSrc: Url,
    val introduction: String,
    val categoryId: String?,
    val isAuthorized: Boolean,
) {
    companion object {
        fun fromUser(user: User): GetUserProfileResponse {
            return GetUserProfileResponse(
                name = user.name,
                profileImgSrc = user.thumbnailUrl,
                introduction = user.introduction,
                categoryId = user.categoryIdentifier?.value,
                isAuthorized = false
            )
        }
    }
}
