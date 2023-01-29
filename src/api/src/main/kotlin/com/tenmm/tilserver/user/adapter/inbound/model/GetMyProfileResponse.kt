package com.tenmm.tilserver.user.adapter.inbound.model

data class GetMyProfileResponse(
    val name: String,
    val profileImgSrc: String,
    val description: String,
    val categoryId: String,
    val postCount: Long,
    val continuousUploadCount: Long,
    val links: List<Link>,
    val shortCuts: List<ShortCut>,
)

data class ShortCut(
    val name: String,
    val url: String,
)
