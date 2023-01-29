package com.tenmm.tilserver.user.adapter.inbound.model

data class GetUserProfileResponse(
    val name: String,
    val profileImgSrc: String,
    val description: String,
    val categoryId: String,
    val postCount: Long,
    val continuousUploadCount: Long,
    val links: List<Link>,
)

data class Link(
    val type: String,
    val src: String,
)
