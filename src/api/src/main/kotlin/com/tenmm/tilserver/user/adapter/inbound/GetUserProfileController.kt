package com.tenmm.tilserver.user.adapter.inbound

import com.tenmm.tilserver.user.adapter.inbound.model.GetUserProfileResponse
import com.tenmm.tilserver.user.application.inbound.GetUserUseCase
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/user/{path}")
class GetUserProfileController(
    private val getUserUseCase: GetUserUseCase,
) {
    @GetMapping
    fun getUserProfile(
        @PathVariable path: String,
    ): GetUserProfileResponse {
        val user = getUserUseCase.getByPath(path)
        return GetUserProfileResponse.fromUser(user)
    }
}
