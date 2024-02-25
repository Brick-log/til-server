package com.tenmm.tilserver.retrospect.adapter.inbound

import com.tenmm.tilserver.common.exception.ErrorResponse
import com.tenmm.tilserver.retrospect.application.inbound.GetUserRetrospectUseCase

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag

import org.springframework.web.bind.annotation.RestController
import java.sql.Timestamp
import java.time.Instant
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RequestMapping
import com.tenmm.tilserver.retrospect.adapter.inbound.Model.GetUserRetrospectResponseModel
import com.tenmm.tilserver.retrospect.adapter.inbound.Model.GetRetrospectMetaResponseModel
import com.tenmm.tilserver.user.application.inbound.GetUserUseCase

import com.tenmm.tilserver.security.domain.UserAuthInfo
import com.tenmm.tilserver.common.security.annotation.OptionalAuthentication

@RestController
@RequestMapping("/v1/retrospect")
@Tag(name = "Retrospect")
class GetUserRetrospectController(
    private val getUserRetrospectUseCase: GetUserRetrospectUseCase,
    private val getUserUseCase: GetUserUseCase,
) {
    @GetMapping("{path}")
    @Operation(
        summary = "사용자별 회고 조회",
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "성공"
            ),
            ApiResponse(
                responseCode = "401",
                description = "로그인 하지 않은 사용자",
                content = [Content(schema = Schema(implementation = ErrorResponse::class))]
            ),
            ApiResponse(
                responseCode = "500",
                description = "서버에러",
                content = [Content(schema = Schema(implementation = ErrorResponse::class))]
            )
        ]
    )
    @OptionalAuthentication
    suspend fun getRetrospect(
        userAuthInfo: UserAuthInfo?,
        @PathVariable path: String,
        @RequestParam size: Int,
        @RequestParam(required = false) to: Long? = null,
        @RequestParam(required = false) from: Long? = null,
        @RequestParam(required = false) pageToken: String? = null,
    ): GetUserRetrospectResponseModel {
        val authPath = if (userAuthInfo != null) {
            try {
                getUserUseCase.getByIdentifier(userAuthInfo.userIdentifier).path
            } catch (e: Exception) {
                ""
            }
        } else {
            ""
        }
        val retrospectListResult = getUserRetrospectUseCase.getRetrospectListByNameAndDateWithPageToken(
            path = path,
            to = to?.let { Timestamp.from(Instant.ofEpochSecond(it)) } ?: Timestamp.from(
                Instant.ofEpochSecond(1909230603)
            ),
            from = from?.let { Timestamp.from(Instant.ofEpochSecond(it)) }
                ?: Timestamp.from(Instant.ofEpochSecond(0)),
            size = size,
            pageToken = pageToken,
            isSecret = if (authPath == path) true else false
        )
        return retrospectListResult
    }

    @GetMapping("/{path}/meta")
    @Operation(
        summary = "잔디 조회",
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "성공"
            ),
            ApiResponse(
                responseCode = "401",
                description = "로그인 하지 않은 사용자",
                content = [Content(schema = Schema(implementation = ErrorResponse::class))]
            ),
            ApiResponse(
                responseCode = "500",
                description = "서버에러",
                content = [Content(schema = Schema(implementation = ErrorResponse::class))]
            )
        ]
    )
    suspend fun getRetrospectMeta(
        @PathVariable path: String,
        @RequestParam to: Long,
        @RequestParam from: Long,
    ): GetRetrospectMetaResponseModel {
        val retrospectMetaResult = getUserRetrospectUseCase.getRetrospectMetaListByNameAndDate(
            path = path,
            to = Timestamp.from(Instant.ofEpochSecond(to)),
            from = Timestamp.from(Instant.ofEpochSecond(from))
        )
        return retrospectMetaResult
    }
}
