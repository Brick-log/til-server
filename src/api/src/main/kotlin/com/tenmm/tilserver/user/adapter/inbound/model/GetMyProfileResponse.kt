package com.tenmm.tilserver.user.adapter.inbound.model

import com.tenmm.tilserver.account.domain.Account
import com.tenmm.tilserver.account.domain.OAuthType
import com.tenmm.tilserver.category.domain.Category
import com.tenmm.tilserver.user.domain.User
import org.apache.commons.lang3.StringUtils

data class GetMyProfileResponse(
    val email: String,
    val oAuthType: OAuthType,
    val name: String,
    val path: String,
    val profileImgSrc: String,
    val introduction: String,
    val categoryIdentifier: String?,
    val categoryName: String?,
    val mailAgreement: Boolean,
) {
    companion object {
        fun fromUser(user: User, category: Category?, account: Account): GetMyProfileResponse {
            return GetMyProfileResponse(
                name = user.name,
                path = user.path,
                profileImgSrc = user.thumbnailUrl.value,
                introduction = user.introduction ?: StringUtils.EMPTY,
                categoryIdentifier = user.categoryIdentifier?.value,
                categoryName = category?.name,
                email = account.email.value,
                oAuthType = account.oAuthType,
                mailAgreement = account.isSpamNotificationAgreed
            )
        }
    }
}
