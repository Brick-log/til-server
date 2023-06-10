package com.tenmm.tilserver.common.exception

import com.tenmm.tilserver.common.domain.ServerBaseException
import io.jsonwebtoken.ExpiredJwtException
import mu.KotlinLogging
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

private val logger = KotlinLogging.logger {}

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ServerBaseException::class)
    fun handleException(e: ServerBaseException): ErrorResponse {
        return ErrorResponse(e.errorCode, e.description)
    }

    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    @ExceptionHandler(ExpiredJwtException::class)
    fun handleExpiredJwtException(e: ExpiredJwtException): ErrorResponse {
        return ErrorResponse(ErrorCode.TOKEN_EXPIRED, "Access Token is Expired")
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable::class)
    fun handleUnknownException(e: Throwable): ErrorResponse {
        logger.error(e) { "Unknown Server Exception" }
        return ErrorResponse(ErrorCode.UNKNOWN_SERVER_ERROR, e.stackTraceToString())
    }
}
