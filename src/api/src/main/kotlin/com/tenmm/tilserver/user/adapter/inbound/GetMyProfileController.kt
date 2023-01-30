package com.tenmm.tilserver.user.adapter.inbound

import com.tenmm.tilserver.user.adapter.inbound.model.GetMyProfileResponse
import com.tenmm.tilserver.user.adapter.inbound.model.Link
import com.tenmm.tilserver.user.adapter.inbound.model.ShortCut
import org.apache.commons.lang3.RandomStringUtils
import org.apache.commons.lang3.RandomUtils
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class GetMyProfileController {
    @GetMapping("/v1/my/profile")
    fun getMyProfile(): GetMyProfileResponse {
        return GetMyProfileResponse(
            name = RandomStringUtils.random(100),
            profileImgSrc = RandomStringUtils.random(100),
            description = RandomStringUtils.random(100),
            categoryId = RandomStringUtils.random(100),
            postCount = RandomUtils.nextLong(),
            continuousUploadCount = RandomUtils.nextLong(),
            links = listOf(
                Link(
                    RandomStringUtils.random(100),
                    RandomStringUtils.random(100)
                )
            ),
            shortCuts = listOf(
                ShortCut(
                    RandomStringUtils.random(100),
                    RandomStringUtils.random(100)
                )
            )
        )
    }
}
