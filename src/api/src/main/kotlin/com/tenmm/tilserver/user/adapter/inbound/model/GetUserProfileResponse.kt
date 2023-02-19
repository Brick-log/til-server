package com.tenmm.tilserver.user.adapter.inbound.model

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.common.domain.Url
import com.tenmm.tilserver.user.domain.User

data class GetUserProfileResponse(
    val name: String,
    val profileImgSrc: Url,
    val introduction: String,
    val categoryId: Identifier?,
) {
    companion object {
        fun fromUser(user: User): GetUserProfileResponse {
            return GetUserProfileResponse(
                name = user.name,
                profileImgSrc = user.thumbnailUrl,
                introduction = user.introduction,
                categoryId = user.categoryIdentifier
            )
        }
    }
}
