package com.tenmm.tilserver.user.adapter.inbound

import com.tenmm.tilserver.user.adapter.inbound.model.ModifyUserProfileResponse
import com.tenmm.tilserver.user.application.inbound.ModifyUserUseCase
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

// @RestController
@RequestMapping("/v1/my")
class ModifyUserProfileController(
    private val modifyUserUseCase: ModifyUserUseCase,
) {
    @PatchMapping("/category")
    fun modifyUserCategory(
        @RequestBody name: String,
    ): ModifyUserProfileResponse {
        TODO()
    }

    @PatchMapping("/introduction")
    fun modifyUserIntroduction(
        @RequestBody introduction: String,
    ): ModifyUserProfileResponse {
        TODO()
    }

    @PatchMapping("/path")
    fun modifyUserPath(
        @RequestBody path: String,
    ): ModifyUserProfileResponse {
        TODO()
    }

    @PatchMapping("/name")
    fun modifyUserName(
        @RequestBody name: String,
    ): ModifyUserProfileResponse {
        TODO()
    }
}
