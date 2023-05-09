package com.tenmm.tilserver.user.adapter.inbound

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.common.domain.Url
import com.tenmm.tilserver.common.domain.toIdentifier
import com.tenmm.tilserver.common.exception.ErrorResponse
import com.tenmm.tilserver.common.security.annotation.RequiredAuthentication
import com.tenmm.tilserver.security.domain.UserAuthInfo
import com.tenmm.tilserver.user.adapter.inbound.model.ModifyUserProfileResponse
import com.tenmm.tilserver.user.adapter.inbound.model.ModifyUserRequest
import com.tenmm.tilserver.user.adapter.inbound.model.OnBoardingUserRequest
import com.tenmm.tilserver.user.application.inbound.ModifyUserUseCase
import com.tenmm.tilserver.user.application.inbound.model.ModifyUserCommand
import com.tenmm.tilserver.user.application.inbound.model.OnBoardingUserCommand
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PostMapping
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
    @PostMapping("/onboarding")
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
    @RequiredAuthentication
    fun onBoardingUserProfile(
        userAuthInfo: UserAuthInfo,
        @RequestBody onBoardingUserRequest: OnBoardingUserRequest,
    ): ModifyUserProfileResponse {
        val command = OnBoardingUserCommand(
            userIdentifier = userAuthInfo.userIdentifier,
            categoryIdentifier = onBoardingUserRequest.categoryIdentifier.toIdentifier(),
            mailAgreement = onBoardingUserRequest.isMailAgreement
        )
        return ModifyUserProfileResponse(modifyUserUseCase.onBoardingUserInfo(command).isSuccess)
    }

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
    @RequiredAuthentication
    fun modifyUserProfile(
        userAuthInfo: UserAuthInfo,
        @RequestBody modifyUserRequest: ModifyUserRequest,
    ): ModifyUserProfileResponse {
        val command = ModifyUserCommand(
            userIdentifier = userAuthInfo.userIdentifier,
            categoryIdentifier = Identifier(modifyUserRequest.categoryIdentifier),
            introduction = modifyUserRequest.introduction,
            name = modifyUserRequest.name,
            path = modifyUserRequest.path,
            mailAgreement = modifyUserRequest.isMailAgreement,
            profileImgSrc = Url(modifyUserRequest.profileImgSrc)
        )
        return ModifyUserProfileResponse(modifyUserUseCase.modifyUserInfo(command).isSuccess)
    }
}
