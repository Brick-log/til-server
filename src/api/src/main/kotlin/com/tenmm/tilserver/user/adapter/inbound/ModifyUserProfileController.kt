package com.tenmm.tilserver.user.adapter.inbound

import com.tenmm.tilserver.auth.domain.UserAuthInfo
import com.tenmm.tilserver.common.adapter.inbound.rest.model.ErrorResponse
import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.user.adapter.inbound.model.ModifyUserProfileResponse
import com.tenmm.tilserver.user.adapter.inbound.model.ModifyUserRequest
import com.tenmm.tilserver.user.application.inbound.ModifyUserUseCase
import com.tenmm.tilserver.user.application.inbound.model.ModifyUserCommand
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/my/profile")
@Tag(name = "Profile")
class ModifyUserProfileController(
    private val modifyUserUseCase: ModifyUserUseCase,
) {
    @PutMapping
    @Operation(
        summary = "사용자 정보 업데이트",
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "사용자 정보 업데이트 성공"
            ),
            ApiResponse(
                responseCode = "400",
                description = "잘못된 업데이트 요청 (ex.잘못된 post id)",
                content = [Content(schema = Schema(implementation = ErrorResponse::class))]
            ),
            ApiResponse(
                responseCode = "401",
                description = "로그인 하지 않은 사용자",
                content = [Content(schema = Schema(implementation = ErrorResponse::class))]
            ),
            ApiResponse(
                responseCode = "403",
                description = "접근 권한이 없는 사용자",
                content = [Content(schema = Schema(implementation = ErrorResponse::class))]
            ),
            ApiResponse(
                responseCode = "500",
                description = "서버에러",
                content = [Content(schema = Schema(implementation = ErrorResponse::class))]
            )
        ]
    )
    fun modifyUserProfile(
        userAuthInfo: UserAuthInfo,
        @RequestBody modifyUserRequest: ModifyUserRequest,
    ): ModifyUserProfileResponse {
        val command = ModifyUserCommand(
            userIdentifier = userAuthInfo.userIdentifier,
            categoryIdentifier = Identifier(modifyUserRequest.categoryIdentifier),
            introduction = modifyUserRequest.introduction,
            name = modifyUserRequest.name,
            path = modifyUserRequest.path
        )
        return ModifyUserProfileResponse(modifyUserUseCase.modifyUserInfo(command).isSuccess)
    }
}
