package com.tenmm.tilserver.user.adapter.inbound

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.user.adapter.inbound.model.GetUserProfileResponse
import com.tenmm.tilserver.user.application.inbound.GetUserUseCase
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/my/user")
class GetUserMyProfileController(
    private val getUserUseCase: GetUserUseCase,
) {
    @GetMapping
    fun getUserMyProfile(): GetUserProfileResponse {
        val user = getUserUseCase.getByIdentifier(Identifier.generate())
        return GetUserProfileResponse.fromUser(user)
    }
}
