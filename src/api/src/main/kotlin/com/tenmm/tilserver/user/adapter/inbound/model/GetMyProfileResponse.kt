package com.tenmm.tilserver.user.adapter.inbound.model

import com.tenmm.tilserver.category.domain.Category
import com.tenmm.tilserver.user.domain.User
import org.apache.commons.lang3.StringUtils

data class GetMyProfileResponse(
    val name: String,
    val path: String,
    val profileImgSrc: String,
    val introduction: String,
    val categoryIdentifier: String?,
    val categoryName: String?,
    val isMailAgreement: Boolean,
) {
    companion object {
        fun fromUser(user: User, category: Category?, isMailAgreement: Boolean): GetMyProfileResponse {
            return GetMyProfileResponse(
                name = user.name,
                path = user.path,
                profileImgSrc = user.thumbnailUrl.value,
                introduction = user.introduction ?: StringUtils.EMPTY,
                categoryIdentifier = user.categoryIdentifier?.value,
                categoryName = category?.name,
                isMailAgreement = isMailAgreement
            )
        }
    }
}
