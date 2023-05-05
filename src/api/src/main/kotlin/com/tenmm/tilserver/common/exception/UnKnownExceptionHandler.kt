package com.tenmm.tilserver.common.exception

import io.jsonwebtoken.ExpiredJwtException
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE + 1)
class UnKnownExceptionHandler {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable::class)
    fun handleApiException(ex: Throwable): ErrorResponse {
        return ErrorResponse(ex.message ?: ex.localizedMessage)
    }

    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    @ExceptionHandler(ExpiredJwtException::class)
    fun handleExpiredJwtException(e: ExpiredJwtException): ErrorResponse {
        return ErrorResponse("Access Token is Expired")
    }
}
