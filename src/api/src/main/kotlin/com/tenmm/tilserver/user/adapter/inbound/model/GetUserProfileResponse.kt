package com.tenmm.tilserver.user.adapter.inbound.model

import com.tenmm.tilserver.user.domain.User
import org.apache.commons.lang3.StringUtils

data class GetUserProfileResponse(
    val name: String,
    val path: String,
    val profileImgSrc: String,
    val introduction: String,
    val categoryId: String?,
    val isAuthorized: Boolean,
) {
    companion object {
        fun fromUser(user: User): GetUserProfileResponse {
            return GetUserProfileResponse(
                name = user.name,
                path = user.path,
                profileImgSrc = user.thumbnailUrl.value,
                introduction = user.introduction ?: StringUtils.EMPTY,
                categoryId = user.categoryIdentifier?.value,
                isAuthorized = false
            )
        }
    }
}
