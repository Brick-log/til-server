package com.tenmm.tilserver.user.adapter.inbound

import com.tenmm.tilserver.security.domain.UserAuthInfo
import com.tenmm.tilserver.common.exception.ErrorResponse
import com.tenmm.tilserver.common.security.annotation.RequiredAuthentication
import com.tenmm.tilserver.user.adapter.inbound.model.GetUserProfileResponse
import com.tenmm.tilserver.user.application.inbound.GetUserUseCase
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/my/user")
@Tag(name = "Profile")
class GetUserMyProfileController(
    private val getUserUseCase: GetUserUseCase,
) {
    @GetMapping
    @Operation(
        summary = "내 프로필 조회",
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "내 프로필 조회 성공"
            ),
            ApiResponse(
                responseCode = "401",
                description = "로그인 하지 않은 사용자",
                content = [Content(schema = Schema(implementation = ErrorResponse::class))]
            ),
            ApiResponse(
                responseCode = "404",
                description = "프로필 조회 실패",
                content = [Content(schema = Schema(implementation = ErrorResponse::class))]
            ),
            ApiResponse(
                responseCode = "500",
                description = "서버에러",
                content = [Content(schema = Schema(implementation = ErrorResponse::class))]
            )
        ]
    )
    @RequiredAuthentication
    fun getUserMyProfile(userAuthInfo: UserAuthInfo): GetUserProfileResponse {
        val user = getUserUseCase.getByIdentifier(userAuthInfo.userIdentifier)
        return GetUserProfileResponse.fromUser(user)
    }
}
