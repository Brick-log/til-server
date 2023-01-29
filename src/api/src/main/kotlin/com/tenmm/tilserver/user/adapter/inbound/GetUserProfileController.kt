package com.tenmm.tilserver.user.adapter.inbound

import com.tenmm.tilserver.user.adapter.inbound.model.Block
import com.tenmm.tilserver.user.adapter.inbound.model.GetBlocksResponse
import com.tenmm.tilserver.user.adapter.inbound.model.GetUserProfileResponse
import com.tenmm.tilserver.user.adapter.inbound.model.Link
import org.apache.commons.lang3.RandomStringUtils
import org.apache.commons.lang3.RandomUtils
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/user/{name}")
class GetUserProfileController {
    @GetMapping
    fun getUserProfile(
        @PathVariable name: String,
    ): GetUserProfileResponse {
        return GetUserProfileResponse(
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
        )
    }

    @GetMapping("/blocks")
    fun getBlocks(
        @PathVariable name: String,
        @RequestParam to: String,
    ): GetBlocksResponse {
        return GetBlocksResponse(
            listOf(
                Block(
                    RandomStringUtils.random(100),
                    RandomStringUtils.random(100),
                    RandomUtils.nextLong()
                )
            )
        )
    }
}
