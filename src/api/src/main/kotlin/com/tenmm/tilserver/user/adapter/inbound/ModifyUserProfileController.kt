package com.tenmm.tilserver.user.adapter.inbound

import com.tenmm.tilserver.user.adapter.inbound.model.ModifyUserCategoryRequest
import com.tenmm.tilserver.user.adapter.inbound.model.ModifyUserIntroductionRequest
import com.tenmm.tilserver.user.adapter.inbound.model.ModifyUserNameRequest
import com.tenmm.tilserver.user.adapter.inbound.model.ModifyUserPathRequest
import com.tenmm.tilserver.user.adapter.inbound.model.ModifyUserProfileResponse
import com.tenmm.tilserver.user.application.inbound.ModifyUserUseCase
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/my")
class ModifyUserProfileController(
    private val modifyUserUseCase: ModifyUserUseCase,
) {
    @PatchMapping("/category")
    fun modifyUserCategory(
        @RequestBody modifyUserCategoryRequest: ModifyUserCategoryRequest,
    ): ModifyUserProfileResponse {
        return ModifyUserProfileResponse(true)
    }

    @PatchMapping("/introduction")
    fun modifyUserIntroduction(
        @RequestBody modifyUserIntroductionRequest: ModifyUserIntroductionRequest,
    ): ModifyUserProfileResponse {
        return ModifyUserProfileResponse(true)
    }

    @PatchMapping("/path")
    fun modifyUserPath(
        @RequestBody modifyUserPathRequest: ModifyUserPathRequest,
    ): ModifyUserProfileResponse {
        return ModifyUserProfileResponse(true)
    }

    @PatchMapping("/name")
    fun modifyUserName(
        @RequestBody modifyUserNameRequest: ModifyUserNameRequest,
    ): ModifyUserProfileResponse {
        return ModifyUserProfileResponse(true)
    }
}
