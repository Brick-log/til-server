package com.tenmm.tilserver.user.adapter.inbound

import com.tenmm.tilserver.common.adapter.inbound.model.ErrorResponse
import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.common.domain.Url
import com.tenmm.tilserver.user.adapter.inbound.model.GetUserProfileResponse
import com.tenmm.tilserver.user.application.inbound.GetUserUseCase
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.apache.commons.lang3.RandomStringUtils
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/user/{path}")
@Tag(name = "Profile")
class GetUserProfileController(
    private val getUserUseCase: GetUserUseCase,
) {
    @GetMapping
    @Operation(
        summary = "사용자 프로필 조회",
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "사용자 프로필 조회 성공"
            ),
            ApiResponse(
                responseCode = "400",
                description = "잘못된 path 요청 (ex.잘못된 post id)",
                content = [Content(schema = Schema(implementation = ErrorResponse::class))]
            ),
            ApiResponse(
                responseCode = "404",
                description = "path 조회 실패",
                content = [Content(schema = Schema(implementation = ErrorResponse::class))]
            ),
            ApiResponse(
                responseCode = "500",
                description = "서버에러",
                content = [Content(schema = Schema(implementation = ErrorResponse::class))]
            )
        ]
    )
    fun getUserProfile(
        @PathVariable path: String,
    ): GetUserProfileResponse {
//        val user = getUserUseCase.getByPath(path)
//        return GetUserProfileResponse.fromUser(user)
        return GetUserProfileResponse(
            name = RandomStringUtils.random(10),
            profileImgSrc = Url("https://www.naver.com/"),
            introduction = RandomStringUtils.random(10),
            categoryId = Identifier.generate().value,
            isAuthorized = false
        )
    }
}
