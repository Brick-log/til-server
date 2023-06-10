package com.tenmm.tilserver.common.exception

data class ErrorResponse(
    val errorCode: ErrorCode,
    val description: String,
)

enum class ErrorCode {
    SIGN_UP_FAIL,
    USER_MODIFY_FAIL,
    INVALID_ARGUMENT,
    USER_NOT_FOUND,
    POST_NOT_FOUND,
    POST_MODIFY_FAIL,
    CRAWLING_RESULT_NOT_FOUND,
    CATEGORY_NOT_FOUND,
    UNAUTHORIZED_USER,
    TOKEN_EXPIRED,
    OAUTH_FAIL,
    UNKNOWN_SERVER_ERROR
}
